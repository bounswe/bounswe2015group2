package edu.boun.cmpe451.group2.android.recipe;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import retrofit.Call;
import retrofit.Response;

public class SearchActivity extends AppCompatActivity {

    ControllerInterface api;
    private ListView searchList;
    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_app_bar);

        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();
        handleIntent(getIntent());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchList = (ListView) findViewById(R.id.search_list);

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipeList.get(position);
                Intent detailIntent = new Intent(getApplicationContext(), RecipeViewActivity.class);
                detailIntent.putExtra(RecipeViewActivity.ARG_ITEM_ID, recipe.getId().toString());
                startActivity(detailIntent);
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            GetSearchResults getSearchResults = new GetSearchResults(query);
            getSearchResults.execute();
        }
    }

    public class GetSearchResults extends AsyncTask<Void, Void,Response<List<Recipe>>>{

        private final String query;

        GetSearchResults(String query) {
            this.query = query;
        }

        @Override
        protected Response<List<Recipe>> doInBackground(Void... params) {
            Call<List<Recipe>> call = api.search(query);
            try {
                return call.execute();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<List<Recipe>> response) {
            recipeList  = response.body();
            ArrayAdapter<Recipe> recipeAdapter = new RecipeAdapter(getApplicationContext(), 0,recipeList);
            searchList.setAdapter(recipeAdapter);
        }

        @Override
        protected void onCancelled() {
        }
    }
}
