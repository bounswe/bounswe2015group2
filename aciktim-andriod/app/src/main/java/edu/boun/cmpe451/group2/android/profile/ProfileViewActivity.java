package edu.boun.cmpe451.group2.android.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.Recipe;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.recipe.RecipeAdapter;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProfileViewActivity extends AppCompatActivity {

    private ControllerInterface api;
    private String api_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        Intent intent = getIntent();

        api_key = intent.getStringExtra("api_key");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-52-28-126-194.eu-central-1.compute.amazonaws.com:8080/aciktim/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ControllerInterface.class);
        GetProfileTask profileTask = new GetProfileTask();
        profileTask.execute();
    }

    public class GetProfileTask extends AsyncTask<Void, Void, Response<User>> {

        @Override
        protected Response<User> doInBackground(Void... params) {
            Call<User> call = api.getUser(api_key);
            try {
                return  call.execute();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Response<User> response) {
            User user = response.body();
            TextView profileNameTextView = (TextView) findViewById(R.id.profile_name);
            TextView profileUserNameTextView = (TextView) findViewById(R.id.profile_user_name);
            TextView profileEMailTextView = (TextView) findViewById(R.id.profile_email);

            profileUserNameTextView.setText(user.username);
            profileNameTextView.setText(user.full_name);
            profileEMailTextView.setText(user.email);
        }

        @Override
        protected void onCancelled() {

        }
    }
}
