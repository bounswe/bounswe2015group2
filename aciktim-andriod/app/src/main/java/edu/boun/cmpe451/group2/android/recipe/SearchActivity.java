package edu.boun.cmpe451.group2.android.recipe;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import retrofit.Call;
import retrofit.Response;

public class SearchActivity extends ListActivity {

    ControllerInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handleIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            ApiProxy apiProxy = new ApiProxy();
            api = apiProxy.getApi();

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
            List<Recipe> recipeList = response.body();
            ArrayAdapter<Recipe> recipeAdapter = new RecipeAdapter(getApplicationContext(), 0,recipeList);
            setListAdapter(recipeAdapter);
        }

        @Override
        protected void onCancelled() {
        }
    }
}
