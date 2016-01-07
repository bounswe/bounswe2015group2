package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;

import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Ingredient;
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

    List<Ingredient> ingredientListView;
    String ingredient_view = "";
    String nutrition_view = "";

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

            Button view_ingredient_button = (Button) findViewById(R.id.recipe_view_ingredient_button);
            Button view_nutrition_button = (Button) findViewById(R.id.recipe_view_nutrition_value_button);

            title.setText(recipe.getName());
            description.setText(recipe.getDescription());
            ImageLoader.getInstance().displayImage(recipe.getPictureAddress(), imageView, options);

            view_ingredient_button .setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    ingredientListView = recipe.getIngredientList();
                    ingredient_view = "";

                    for( int j = 0; j < ingredientListView.size(); j++ ){
                        ingredient_view += "" + (j + 1) + ") " + ingredientListView.get( j ).getName() + " -> ";
                        ingredient_view += "Calorie: " + ingredientListView.get( j ).getCalories() + ", ";
                        ingredient_view += "Carbohydrate: " + ingredientListView.get( j ).getCarbohydrate() + ", ";
                        ingredient_view += "Protein: " + ingredientListView.get( j ).getProtein() + ", ";
                        ingredient_view += "Fat: " + ingredientListView.get( j ).getFat() + ", ";
                        ingredient_view += "Quantity: " + ingredientListView.get( j ).amount + " " + ingredientListView.get( j ).getUnitName();
                        ingredient_view += "\n\n";
                    }

                    AlertDialog.Builder diyalog =
                            new AlertDialog.Builder( RecipeViewActivity.this);

                    diyalog.setMessage( ingredient_view )
                            .setCancelable(false)
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    diyalog.create().show();

                }
            });

            view_nutrition_button .setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    nutrition_view = "";

                    nutrition_view += "Calorie: " + recipe.getTotalCal() + "\n";
                    nutrition_view += "Carbohydrate: " + recipe.getTotalCarb() + "\n";
                    nutrition_view += "Protein: " + recipe.getTotalProtein() + "\n";
                    nutrition_view += "Fat: " + recipe.getTotalProtein() + "\n";

                    AlertDialog.Builder diyalog =
                            new AlertDialog.Builder( RecipeViewActivity.this);

                    diyalog.setMessage( nutrition_view )
                            .setCancelable(false)
                            .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    diyalog.create().show();

                }
            });

        }

        @Override
        protected void onCancelled() {
        }
    }
}
