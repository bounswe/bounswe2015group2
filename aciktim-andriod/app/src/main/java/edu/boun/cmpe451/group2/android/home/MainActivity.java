package edu.boun.cmpe451.group2.android.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import edu.boun.cmpe451.group2.android.DailyConsumptionFragment;
import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;
import edu.boun.cmpe451.group2.android.api.User;
import edu.boun.cmpe451.group2.android.profile.ProfileViewActivity;
import edu.boun.cmpe451.group2.android.recipe.RecipeListActivity;
import edu.boun.cmpe451.group2.android.recipe.RecipeListFragment;

import edu.boun.cmpe451.group2.android.restaurant.RestaurantListActivity;
import retrofit.Call;
import retrofit.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RecipeListFragment.Callbacks{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private ControllerInterface api;
    private String api_key;
    private User user;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        Context context = getApplication();
        sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Intent intent =  getIntent();
        if(intent.hasExtra("api_key")){
            api_key = intent.getStringExtra("api_key");
            editor = sharedPref.edit();
            editor.putString(getString(R.string.api_key), api_key);
            editor.commit();
        } else {
            api_key = sharedPref.getString(getString(R.string.api_key), "");
        }



        ApiProxy apiProxy = new ApiProxy();
        api = apiProxy.getApi();

        GetUserTask getUserTask = new GetUserTask();
        getUserTask.execute();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, api_key, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            editor.remove(getString(R.string.api_key));
            editor.commit();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        getMenuInflater().inflate(R.menu.menu_tab, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.search);


        SearchView searchView = null;
        if (searchItem != null) {
            searchView =  (SearchView) menu.findItem(R.id.search).getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.search:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorites) {
            Intent intent = new Intent(this,RecipeListActivity.class);
            intent.putExtra("user_id",user.getId());
            startActivity(intent);
        } else if (id == R.id.nav_recipes) {
            Intent intent = new Intent(this,RecipeListActivity.class);
            intent.putExtra("user_id",user.getId());
            startActivity(intent);
        }  else if (id == R.id.nav_restaurants) {
            Intent intent = new Intent(this,RestaurantListActivity.class);
            intent.putExtra("user_id",user.getId());
            startActivity(intent);
        }else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this,ProfileViewActivity.class);
            intent.putExtra("api_key", api_key);
            startActivity(intent);
        } else if (id == R.id.nav_sign_out) {
            editor.remove(getString(R.string.api_key));
            editor.commit();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(String id) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putInt("fragment_list_type",0);
                    bundle.putString("api_key",api_key);
                    //bundle.putParcelable("user",user);
                    Fragment fragment = new RecipeListFragment();
                    fragment.setArguments(bundle);
                    return fragment;
                case 1:
                    Bundle bundle_2 = new Bundle();
                    bundle_2.putString("api_key",api_key);
                    Fragment daily_fragment = new DailyConsumptionFragment();
                    daily_fragment.setArguments(bundle_2);
                    return daily_fragment;
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "DAILY CONSUMPTION";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class GetUserTask extends AsyncTask<Void, Void, Response<User>> {
        @Override
        protected Response<User> doInBackground(Void... params) {
            Call<User> call = api.getUser(api_key);
            try {
                return call.execute();
            } catch (IOException e) {
                e.printStackTrace();
                return null;

            }
        }

        @Override
        protected void onPostExecute(final Response<User> response) {
            user = response.body();
            TextView nav_userNameView = (TextView) findViewById(R.id.nav_view_userName);
            TextView nav_userEmailView = (TextView) findViewById(R.id.nav_view_emailName);
            if(user != null) {
                nav_userNameView.setText(user.full_name);
                nav_userEmailView.setText(user.getEmail());
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}
