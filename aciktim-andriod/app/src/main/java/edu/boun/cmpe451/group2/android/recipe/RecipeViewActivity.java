package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import retrofit.Call;
import retrofit.Response;

/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeListActivity}.
 */
public class RecipeViewActivity extends AppCompatActivity {

    public static final String ARG_ITEM_ID = "item_id";
    private Long recipe_id;
    private ControllerInterface api;
    private Recipe recipe;

    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            recipe_id =  Long.parseLong(intent.getStringExtra(ARG_ITEM_ID));
        }

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedVignetteBitmapDisplayer(4,5))
                .build();

        GetRecipeTask recipeTask = new GetRecipeTask(recipe_id);
        recipeTask.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class GetRecipeTask extends AsyncTask<Void, Void, Response<Recipe>> {
        Long recipe_id;
        public GetRecipeTask(Long recipe_id){
            this.recipe_id = recipe_id;
        }



        @Override
        protected Response<Recipe> doInBackground(Void... params) {
            try {
                Call<Recipe> call = api.getRecipe(recipe_id);
                return call.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<Recipe> response) {
            recipe = response.body();
            ImageView imageView =(ImageView) findViewById(R.id.photo);
            TextView title =(TextView) findViewById(R.id.article_title);
            TextView description =(TextView) findViewById(R.id.article_body);

            title.setText(recipe.getName());
            description.setText(recipe.getDescription());
            ImageLoader.getInstance().displayImage(recipe.getPictureAddress(),imageView, options);


        }

        @Override
        protected void onCancelled() {
        }
    }
}
