package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import edu.boun.cmpe451.group2.android.LoginActivity;
import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Mustafa Taha on 7.01.2016.
 */
public class RecommendationFragment extends Fragment {

    private ControllerInterface api;

    private Long userId;
    ListView recommendationList;
    List<Recipe> recipeList;

    public RecommendationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_recommendation, container, false);
        recommendationList = (ListView)view.findViewById(R.id.recommendation_list);
        recommendationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userId =Long.parseLong((String) getArguments().get("user_id"));


        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();

        GetRecommendationTask getUserTask = new GetRecommendationTask(userId);
        getUserTask.execute();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public class GetRecommendationTask extends AsyncTask<Void, Void, Response<List<Recipe>>> {

        private Long userId;

        GetRecommendationTask(Long userId) {
            this.userId = userId;
        }

        @Override
        protected Response<List<Recipe>> doInBackground(Void... params) {
            Call<List<Recipe>> call = api.getRecommendations(userId);
            try {
                return call.execute();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<List<Recipe>> response) {
            recipeList=  response.body();
            ArrayAdapter<Recipe> recipeAdapter = new RecipeAdapter(getContext(), 0, recipeList);
            recommendationList.setAdapter(recipeAdapter);
        }

        @Override
        protected void onCancelled() {
        }
    }
}
