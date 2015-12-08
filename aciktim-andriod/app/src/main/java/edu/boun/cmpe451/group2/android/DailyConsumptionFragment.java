package edu.boun.cmpe451.group2.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.home.MainActivity;
import edu.boun.cmpe451.group2.android.recipe.RecipeListAdapter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by AhmtBrK on 07/12/15.
 */
public class DailyConsumptionFragment extends Fragment {

    ListView recipeLV;
    ProgressBar loadingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_consumption, container, false);

        recipeLV = (ListView) view.findViewById(R.id.recipeLV);
        loadingBar = (ProgressBar) view.findViewById(R.id.loadingBar);

        getDailyConsumptions();

        return view;
    }

    private void getDailyConsumptions() {
        ApiProxy apiProxy = new ApiProxy();
        final ControllerInterface api = apiProxy.getApi();



        String apiKey = getArguments().getString("api_key");
        Call<User> userRequest = api.getUser(apiKey);
        userRequest.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {

                User user = response.body();

                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                if(user!=null){
                    Call<List<Recipe>> dailyConsumptionRequest = api.getDailyConsumption(Long.valueOf(user.getId()), formatter.format(currentDate));
                    dailyConsumptionRequest.enqueue(new Callback<List<Recipe>>() {
                        @Override
                        public void onResponse(Response<List<Recipe>> response, Retrofit retrofit) {
                            List<Recipe> recipes = response.body();
                            RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getActivity(), recipes);
                            recipeLV.setAdapter(recipeListAdapter);
                            loadingBar.setVisibility(View.GONE);
                            recipeLV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            loadingBar.setVisibility(View.GONE);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                loadingBar.setVisibility(View.GONE);
            }
        });

    }
}
