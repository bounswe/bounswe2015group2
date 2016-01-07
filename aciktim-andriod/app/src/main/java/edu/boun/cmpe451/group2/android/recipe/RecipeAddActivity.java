package edu.boun.cmpe451.group2.android.recipe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.SemanticTagActivity;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ApiResponse;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Ingredient;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.SemanticTag;
import edu.boun.cmpe451.group2.android.api.Tag;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.ingredient.IngredientAdapter;
import edu.boun.cmpe451.group2.android.ingredient.IngredientItem;
import edu.boun.cmpe451.group2.android.ingredient.IngredientNutrition;
import retrofit.Call;
import retrofit.Response;

/**
 * A recipe-add screen
 */
public class RecipeAddActivity extends AppCompatActivity {

    private static final int REQ_SEMANTIC_TAG_ACTIVITY = 999;
    /**
     * Id to identity READ_CONTACTS permission request.
     * <p/>
     * private static final int REQUEST_READ_CONTACTS = 0;
     * <p/>
     * /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     * <p/>
     * private static final String[] DUMMY_CREDENTIALS = new String[]{
     * "foo@example.com:hello", "bar@example.com:world"
     * };
     */

    // UI references.
    EditText recipeName;
    EditText recipeDescription;
    EditText recipeImageUrl;
    Button recipeAddButton;
    ListView listView;

    // Ingredients
    Dialog ingredientAddDialog;
    EditText ingredientName;
    TextView ingredientListText;
    ListView ingredientListView;
    Button ingredientSearch;
    Button ingredient_add_button;
    Button cancel_button;
    Spinner ingredient_add_spinner;
    EditText ingredientQuantity;

    List<IngredientItem> ingredientItem = new ArrayList<IngredientItem>();
    List<IngredientNutrition> ingredientNutrition = new ArrayList<IngredientNutrition>();
    String ingName = "";
    String ingNDBNO = "";
    String quantity = "100";

    String deneme = "";
    // Ingredients

    String myRecipeName = "";
    String myRecipeDescription = "";
    String myRecipeImageURL = "";

    List<SemanticTag> semanticTags = new ArrayList<>();

    ProgressDialog progressDialogForSearchIngredient;

    // API
    ApiProxy apiProxy = new ApiProxy();
    ControllerInterface api = apiProxy.getApi();
    String api_key;
    String user_id;
    // API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_add);

        /*final String */user_id = getIntent().getStringExtra("user_id");

        Toast.makeText(getApplicationContext(),user_id+"",Toast.LENGTH_SHORT).show();

        TabHost th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag 1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("RECIPE DETAIL");
        th.addTab(specs);

        specs = th.newTabSpec("tag 2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("RECIPE DESCRIPTION");
        th.addTab(specs);

        specs = th.newTabSpec("tag 3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("ADD INGREDIENTS");
        th.addTab(specs);

        specs = th.newTabSpec("tag 4");
        specs.setContent(R.id.tab4);
        specs.setIndicator("INGREDIENTS DETAIL");
        th.addTab(specs);

        recipeName = (EditText) findViewById(R.id.recipe_add_name_text);
        recipeImageUrl = (EditText) findViewById(R.id.recipe_add_image_url_);
        recipeDescription = (EditText) findViewById(R.id.recipe_add_description_text);

        listView = (ListView) findViewById(R.id.listView);
        SemanticTagAdapter semanticTagAdapter = new SemanticTagAdapter(this, semanticTags);
        listView.setAdapter(semanticTagAdapter);

        Button semanticTagAddButton = (Button) findViewById(R.id.semantic_tag_add_button);
        semanticTagAddButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), SemanticTagActivity.class), REQ_SEMANTIC_TAG_ACTIVITY);
            }
        });

        recipeAddButton = (Button) findViewById(R.id.recipe_add_button);

        recipeAddButton .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                myRecipeName = recipeName.getText().toString();
                myRecipeDescription = recipeDescription.getText().toString();
                myRecipeImageURL =  recipeImageUrl.getText().toString();

                // API
                Intent intent = getIntent();
                api_key = intent.getStringExtra("api_key");
                DenemeTask profileTask = new DenemeTask();
                profileTask.execute();
                // API

            }
        });

        // my code start here
        ingredientName = (EditText) findViewById(R.id.ingredient_add_name);
        ingredientListText = (TextView) findViewById(R.id.ingredient_add_list);
        ingredientListView = (ListView) findViewById(R.id.ingredient_add_listView);
        ingredientSearch = (Button) findViewById(R.id.ingredient_add_search_button);

        ingredientSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ingredientItem.clear();
                ingredientListView.setAdapter(null);

                progressDialogForSearchIngredient = new ProgressDialog(view.getContext());
                progressDialogForSearchIngredient.setCancelable(true);
                progressDialogForSearchIngredient.setMessage("Ingredients are being searched ...");
                progressDialogForSearchIngredient.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialogForSearchIngredient.setProgress(0);
                progressDialogForSearchIngredient.setMax(100);
                progressDialogForSearchIngredient.show();

                new JSONtask().execute("http://api.nal.usda.gov/ndb/search/?format=json&q=" + ingredientName.getText().toString() + "&sort=r&max=10&offset=0&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX");

            }
        });

        ingredientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                String ing = ingredientItem.get(position).getName();

                ingredientAddDialog = new Dialog(RecipeAddActivity.this);
                ingredientAddDialog.setContentView(R.layout.add_ingredient_custom_dialog);
                ingredient_add_button = (Button) ingredientAddDialog.findViewById(R.id.ingredient_add_buttonAdd);
                cancel_button = (Button) ingredientAddDialog.findViewById(R.id.ingredient_add_buttonCancel);
                ingredientQuantity = (EditText) ingredientAddDialog.findViewById(R.id.ingredient_add_quantity);

                ingredient_add_spinner = (Spinner) ingredientAddDialog.findViewById(R.id.ingredient_add_spinner);
                String[] quantityTypes = {"gram"};
                ArrayAdapter<String> adapterQuantity = new ArrayAdapter<String>(RecipeAddActivity.this, android.R.layout.simple_spinner_dropdown_item, quantityTypes);
                ingredient_add_spinner.setAdapter(adapterQuantity);

                ingredientAddDialog.setTitle(ing);

                ingredient_add_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        ingName = ingredientItem.get(position).getName();
                        ingNDBNO = ingredientItem.get(position).getNdbno();

                        deneme += "\n" + ingName + " -> ";

                        if (!ingredientQuantity.getText().toString().isEmpty()) {
                            quantity = ingredientQuantity.getText().toString();
                        }

                        progressDialogForSearchIngredient = new ProgressDialog(view.getContext());
                        progressDialogForSearchIngredient.setCancelable(true);
                        progressDialogForSearchIngredient.setMessage("Your ingredient is being added ...");
                        progressDialogForSearchIngredient.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialogForSearchIngredient.setProgress(0);
                        progressDialogForSearchIngredient.setMax(100);
                        progressDialogForSearchIngredient.show();

                        new JSONtaskWithNDBNO().execute("http://api.nal.usda.gov/ndb/reports/?ndbno=" + ingredientItem.get(position).getNdbno() + "&type=b&format=json&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX");

                        ingredientAddDialog.dismiss();
                    }

                });
                cancel_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        ingredientAddDialog.dismiss();

                    }
                });
                ingredientAddDialog.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_SEMANTIC_TAG_ACTIVITY && resultCode == RESULT_OK) {
            String semanticTag = data.getExtras().getString(SemanticTagActivity.EXTRA_TAG_NAME);
            String semanticTagClass = data.getExtras().getString(SemanticTagActivity.EXTRA_TAG_CLASS);
            SemanticTag tempSemanticTag = new SemanticTag();
            tempSemanticTag.setTagName(semanticTag);
            tempSemanticTag.setTagClass(semanticTagClass);
            semanticTags.add(tempSemanticTag);
            ((SemanticTagAdapter)listView.getAdapter()).notifyDataSetChanged();
        }
    }


    /*
     * Data will be gotten from http://api.nal.usda.gov/ndb via that site's api.
     *
     */
    public class JSONtask extends AsyncTask<String, String, List<IngredientItem>> {

        @Override
        protected List<IngredientItem> doInBackground(String... params) {
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

                return ingredientItem;

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
        protected void onPostExecute(List<IngredientItem> result) {
            super.onPostExecute(result);

            progressDialogForSearchIngredient.dismiss();

            if( result != null ) {
                IngredientAdapter adapter = new IngredientAdapter(getApplicationContext(), R.id.ingredient_add_list, result);
                ingredientListView.setAdapter(adapter);
            }

            // if ingredient searched is not in the ingredient list of http://api.nal.usda.gov/ndb
            else {
                AlertDialog.Builder diyalog =
                        new AlertDialog.Builder(RecipeAddActivity.this);

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

    public class JSONtaskWithNDBNO extends AsyncTask<String, String, List<IngredientNutrition>> {

        @Override
        protected List<IngredientNutrition> doInBackground(String... params) {
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

                JSONObject jsonObjectList = jsonRootObject.getJSONObject("report").getJSONObject("food");

                JSONArray jsonArrayItem = jsonObjectList.getJSONArray("nutrients");

                JSONObject jsonObject;

                String energy = "";
                String carb = "";
                String prot = "";
                String fat = "";

                for(int i=0; i < jsonArrayItem.length(); i++) {
                    jsonObject = jsonArrayItem.getJSONObject(i);

                    if( jsonObject.getString("name").equals("Energy") && jsonObject.getString("unit").equals("kcal")){
                        energy = jsonObject.getString("value");
                        deneme += "energy:" + energy;
                    }
                    if(jsonObject.getString("name").equals("Carbohydrate, by difference")){
                        carb = jsonObject.getString("value");;
                        deneme += ", carb:" + carb;
                    }
                    if(jsonObject.getString("name").equals("Protein")){
                        prot = jsonObject.getString("value");
                        deneme += ", prot:" + prot;
                    }
                    if(jsonObject.getString("name").equals("Total lipid (fat)")){
                        fat = jsonObject.getString("value");
                        deneme += ", fat:" + fat;
                    }
                }
                deneme += ", quantity:" + quantity;
                ingredientNutrition.add( new IngredientNutrition( ingName, ingNDBNO, quantity, energy, carb, prot, fat ) );

                return ingredientNutrition;

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
        protected void onPostExecute(List<IngredientNutrition> result) {
            super.onPostExecute(result);

            progressDialogForSearchIngredient.dismiss();

            ingredientListText.setText(deneme);

            Toast.makeText(getApplicationContext(), "Ingredient was added", Toast.LENGTH_SHORT).show();
        }
    }

    public class DenemeTask extends AsyncTask<Void, Void, Response<ApiResponse> > {

        @Override
        protected Response<ApiResponse> doInBackground(Void... params) {

            double totalCal = 0, totalCarb = 0, totalProtein = 0, totalFat = 0;
            double ingRate;
            Recipe myRecipe = new Recipe();
            Ingredient myIngredient;
            List<Ingredient> ingredientList = new LinkedList<>();
            List<Tag> myTagList = new ArrayList<Tag>();

            for( int i = 0; i < ingredientNutrition.size(); i++){

                ingRate = Long.parseLong(ingredientNutrition.get(i).getQuantity()) / 100.0;

                myIngredient = new Ingredient();
                myIngredient.id = Long.parseLong( ingredientNutrition.get( i ).getNDBNO() );
                myIngredient.setName( ingredientNutrition.get( i ).getName() );
                myIngredient.setUnitName("gram");
                myIngredient.setCalories(Double.parseDouble(ingredientNutrition.get(i).getEnergy()) * ingRate);
                myIngredient.setCarbohydrate(Double.parseDouble(ingredientNutrition.get(i).getCarbohydrate()) * ingRate);
                myIngredient.setProtein(Double.parseDouble(ingredientNutrition.get(i).getProtein()) * ingRate);
                myIngredient.setFat(Double.parseDouble(ingredientNutrition.get(i).getFat()) * ingRate);
                myIngredient.amount = Long.parseLong(ingredientNutrition.get(i).getQuantity());
                totalCal += Double.parseDouble( ingredientNutrition.get( i ).getEnergy() ) * ingRate;
                totalCarb += Double.parseDouble( ingredientNutrition.get( i ).getCarbohydrate() ) * ingRate;
                totalProtein += Double.parseDouble( ingredientNutrition.get( i ).getProtein() ) * ingRate;
                totalFat += Double.parseDouble( ingredientNutrition.get( i ).getFat() ) * ingRate;

                ingredientList.add(myIngredient);

            }

            Tag myTag = new Tag();
            for( int a = 0; a < semanticTags.size(); a++){
                myTag.setName( semanticTags.get( a ).getTagName() );
                myTag.setParentTag(semanticTags.get(a).getTagClass());
                myTagList.add( myTag );
            }

            myRecipe.setName( myRecipeName );
            myRecipe.setDescription( myRecipeDescription );
            myRecipe.setPictureAddress( myRecipeImageURL );
            myRecipe.setOwnerID(Long.parseLong(user_id));
            myRecipe.setTotalCal(totalCal);
            myRecipe.setTotalCarb(totalCarb);
            myRecipe.setTotalProtein(totalProtein);
            myRecipe.setTotalFat( totalFat );
            myRecipe.setIngredientList( ingredientList );
            myRecipe.setTagList(myTagList);

            Call<ApiResponse> myResponse = api.addrecipe( myRecipe );
            try {
                return myResponse.execute();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<ApiResponse> response) {

            Toast.makeText(getApplicationContext(), "Recipe was added successfully.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {

        }
    }
}