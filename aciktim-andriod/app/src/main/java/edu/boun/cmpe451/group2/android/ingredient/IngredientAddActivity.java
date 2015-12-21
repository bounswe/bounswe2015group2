package edu.boun.cmpe451.group2.android.ingredient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;

/**
 * Created by Cafer Tayyar YORUK on 06.12.2015.
 * It gets ingredient, which is searched by the user, from http://api.nal.usda.gov/ndb database.
 * Then data associated with the ingredient searched are listed on the screen.
 */
public class IngredientAddActivity extends AppCompatActivity {

    private EditText ingredientName;
    private TextView ingredientListText;
    private ListView ingredientListView;
    private Button ingredientSearch;
    private IngredientList ingredientList = new IngredientList();
    private List<IngredientItem> ingredientItem = new ArrayList<IngredientItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredient_add);

        ingredientName = (EditText) findViewById(R.id.ingredient_add_name);
        ingredientListText = (TextView) findViewById(R.id.ingredient_add_list);
        ingredientListView = (ListView) findViewById(R.id.ingredient_add_listView);
        ingredientSearch = (Button) findViewById(R.id.ingredient_add_search_button);

        ingredientSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ingredientItem.clear();
                ingredientListView.setAdapter(null);
                new JSONtask().execute("http://api.nal.usda.gov/ndb/search/?format=json&q="+ingredientName.getText().toString()+"&sort=r&max=10&offset=0&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX");
            }
        });

        ingredientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                AlertDialog.Builder diyalogOlusturucu =
                        new AlertDialog.Builder(IngredientAddActivity.this);

                diyalogOlusturucu.setMessage( "NDBNO: " + ingredientList.getItem().get( position ).getNdbno() )
                        .setCancelable(false)
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                diyalogOlusturucu.create().show();

            }
        });

    }

    /*
     * Data will be gotten from http://api.nal.usda.gov/ndb via that site's api.
     *
     */
    public class JSONtask extends AsyncTask<String, String, IngredientList> {

        @Override
        protected IngredientList doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream stream;
            BufferedReader reader = null;
            StringBuffer buffer;
            String line = "";

            try {
                url = new URL( params[ 0 ] );
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                stream = new BufferedInputStream(urlConnection.getInputStream());
                reader = new BufferedReader(new InputStreamReader( stream ));

                buffer = new StringBuffer();

                while( (line = reader.readLine()) != null ){
                    buffer.append( line );
                }

                String finalJson = buffer.toString();

                JSONObject jsonRootObject = new JSONObject( finalJson );

                JSONObject jsonObjectList = jsonRootObject.getJSONObject("list");

                JSONArray jsonArrayItem = jsonObjectList.getJSONArray("item");
                JSONObject jsonObject;

                String name = "";
                String ndbno = "";

                for(int i=0; i < jsonArrayItem.length(); i++) {
                    jsonObject = jsonArrayItem.getJSONObject( i );
                    name = jsonObject.getString("name");    // Name of the ingredient
                    ndbno = jsonObject.getString("ndbno");  // Ndbno is such an identification number of the ingredient.
                                                            // Every ingredient has its unique ndbno.
                    ingredientItem.add( new IngredientItem( name, ndbno ) );
                }

                ingredientList.setItem( ingredientItem );

                return ingredientList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {

                if( urlConnection != null )
                    urlConnection.disconnect();

                try {

                    if( reader != null )
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(IngredientList result) {
            super.onPostExecute(result);
            if( result != null ) {
                IngredientAdapter adapter = new IngredientAdapter(getApplicationContext(), R.id.ingredient_add_list, result.getItem());
                ingredientListView.setAdapter(adapter);
            }

            // if ingredient searched is not in the ingredient list of http://api.nal.usda.gov/ndb
            else {
                AlertDialog.Builder diyalog =
                        new AlertDialog.Builder(IngredientAddActivity.this);

                diyalog.setMessage( "There is no such an ingredient!" )
                        .setCancelable(false)
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                diyalog.create().show();
            }
        }
    }
}
