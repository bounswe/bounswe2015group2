package edu.boun.cmpe451.group2.android.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
/**
 * Created by Mustafa Taha on 1.12.2015.
 */
public class ApiProxy {
    ​
    public ControllerInterface getApi() {
        return api;
    }
    ​
    private ControllerInterface api;
    ​
    public ApiProxy() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-28-126-194.eu-central-1.compute.amazonaws.com:8080/aciktim/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ControllerInterface.class);
    }
}