package edu.boun.cmpe451.group2.android;

//kullandığımız tüm objectlerin import edilmesi
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;

import edu.boun.cmpe451.group2.android.recipe.RecipeListAdapter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

// ana ekranda 3 tane tab var ve bu tabların hepsi tek bir fragment’e tekabül ediyor.
// fragmentpageradapter aracılığı ile her tab’a bir fragment tayin ediliyor.
// bu yüzden 2.sıradaki dailyconsumption tagı için adapter içinde
// DailyConsumptionFragment türettik



public class DailyConsumptionFragment extends Fragment {   // recipeleri ekranda göstermek için listview

    ListView recipeLV;  // recipeleri sunucudan çekerken progress göstermek için progressbar
    ProgressBar loadingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_consumption, container, false);

        recipeLV = (ListView) view.findViewById(R.id.recipeLV);
        loadingBar = (ProgressBar) view.findViewById(R.id.loadingBar);
        // sunucudan dailyConsumption datalarını çekmek için methodu call et
        //getDailyConsumptions();

        return view;
    }
    // dailyConsumtionu user bazlı çekiyoruz bu sebeple ilk önce user bilgilerini çekip ardına o bilgiler gelince daily datalarını istiyoruz.
    private void getDailyConsumptions() {
        ApiProxy apiProxy = new ApiProxy();
        final ControllerInterface api = apiProxy.getApi();  // retrofit interfacesini kullanabilmek için api türetiyoruz.



        String apiKey = getArguments().getString("api_key");  // login olduğumuzda api_key geliyor onunla user bilgilerini çekeceğiz bu yüzden fragment’i türetirken bunuda gönderiyoruz
        Call<User> userRequest = api.getUser(apiKey);  // user bilgilerini api_key ile çekiyoruz.
        userRequest.enqueue(new Callback<User>() {  // user bilgileri için async request atıyoruz
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {  // user requesti başarılı olursa bu method çağırılır

                User user = response.body();  // user bilgilerini bu şekilde responsenin içinden alıyoruz

                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); // bu formatta bir tarih gönderip bugünün datasını istiyoruz
                if(user!=null){
                    Call<List<Recipe>> dailyConsumptionRequest = api.getDailyConsumption(Long.valueOf(user.getId()), formatter.format(currentDate)); // dailyConsumtion requestini bugünün datası ile oluşturuyoruz
                    dailyConsumptionRequest.enqueue(new Callback<List<Recipe>>() {  // dailyConsumption için async request yolluyoruz
                        @Override
                        public void onResponse(Response<List<Recipe>> response, Retrofit retrofit) {  // dailyConsumption başarılı olarak gelirse bu methoda geliyor.
                            List<Recipe> recipes = response.body();  // Recipe datalarını liste olarak responsenin içinden alıyoruz
                            RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getActivity(), recipes); // Recipe datalarını listviewde göstermek için adaptere veriyoruz.
                            recipeLV.setAdapter(recipeListAdapter); // recipe adapterini listeye verip recipeleri gösteriyoruz.
                            loadingBar.setVisibility(View.GONE);   // işlem bittiği için progress’i gizliyoruz
                            recipeLV.setVisibility(View.VISIBLE);   // recipe dataları geldiği için recipe list’i görünür hale getiriyoruz.
                        }

                        @Override
                        public void onFailure(Throwable t) {   // recipe listesi bir internet veya sunucu sorundan gelmezse burası cagırılır
                            loadingBar.setVisibility(View.GONE);  // işlem olumsuzda olsa bittiği için progress i gizliyoruz.
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {  // user bilgisi bir internet veya sunucu sorunundan ötürü gelmez ise burası çağırılır.
                t.printStackTrace();
                loadingBar.setVisibility(View.GONE);
            }
        });

    }
}
