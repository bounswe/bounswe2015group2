package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.dummy.DummyContent;
import retrofit.Call;
import retrofit.Response;

/**
 * A list fragment representing a list of Recipes. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link }.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class RecipeListFragment extends Fragment {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    List<Recipe> recipeList;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    ControllerInterface api;
    ListView recipeListView;

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {

        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int listType= 0;
        try {
            listType = savedInstanceState.getInt("fragment_list_type");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();
        Long userID = Long.valueOf(59);
        switch (listType){
            case 0:
                GetRecipeListTask recipeListTask = new GetRecipeListTask(userID);
                recipeListTask.execute();
                break;
            case 1:
                //GetRecommendationTask recommendationTask = new GetRecommendationTask(user);
                //recommendationTask.execute();
                //break;
            default:
                GetRecipeListTask listTask = new GetRecipeListTask(userID);
                listTask.execute();

        }
        GetRecipeListTask recipeListTask = new GetRecipeListTask(userID);
        recipeListTask.execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list,container);
        recipeListView = (ListView) view.findViewById(R.id.recipe_list);
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipeList.get(position);
                Intent detailIntent = new Intent(getContext(), RecipeViewActivity.class);
                detailIntent.putExtra(RecipeViewActivity.ARG_ITEM_ID, recipe.getId().toString());
                startActivity(detailIntent);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public static RecipeListFragment newInstance(int list_type) {
        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();
        args.putInt("List Type", list_type);
        fragment.setArguments(args);
        return fragment;
    }

    public class GetRecipeListTask extends AsyncTask<Void, Void, Response<List<Recipe>>> {

        private final Long userId;

        GetRecipeListTask(Long userId) {
            this.userId = userId;
        }

        @Override
        protected Response<List<Recipe>> doInBackground(Void... params) {
            Call<List<Recipe>> call = api.getRecipes(userId);
            try {
                return call.execute();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<List<Recipe>> response) {
            recipeList = response.body();
            ArrayAdapter<Recipe> recipeAdapter = new RecipeAdapter(getContext(), 0,recipeList);
            if(recipeList!=null) {
                recipeListView.setAdapter(recipeAdapter);
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}
