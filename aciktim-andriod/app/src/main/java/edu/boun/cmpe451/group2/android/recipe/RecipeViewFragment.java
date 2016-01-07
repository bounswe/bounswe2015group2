package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.dummy.DummyContent;
import retrofit.Call;
import retrofit.Response;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeViewActivity}
 * on handsets.
 */
public class RecipeViewFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private Long recipe_id;
    private ControllerInterface api;
    private Recipe recipe;
    View rootView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Bundle arguments = getArguments();
            recipe_id = Long.parseLong(arguments.getString(ARG_ITEM_ID));
        }

        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recipe_view, container, false);

        // Show the dummy content as text in a TextView.


        return rootView;
    }

    public class GetRecipeTask extends AsyncTask<Void, Void, Response<Recipe>> {
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


            Activity activity = getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (recipe != null) {
                if (appBarLayout != null) {
                    appBarLayout.setTitle(recipe.description);
                }

                ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(recipe.getName());
            }

        }

        @Override
        protected void onCancelled() {
        }
    }
}
