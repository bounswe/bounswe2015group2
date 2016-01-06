package edu.boun.cmpe451.group2.android.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.api.ApiProxy;
import edu.boun.cmpe451.group2.android.api.ControllerInterface;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeViewActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link RecipeListFragment} and the item details
 * (if present) is a {@link RecipeViewFragment}.
 * <p/>
 * This activity also implements the required
 * {@link RecipeListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class RecipeListActivity extends AppCompatActivity
        implements RecipeListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_app_bar);
        final String user_id = getIntent().getStringExtra("user_id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        Toast.makeText(getApplicationContext(), user_id + "", Toast.LENGTH_SHORT).show();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.recipe_add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecipeAddActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((RecipeListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.recipe_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
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
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method from {@link RecipeListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RecipeViewFragment.ARG_ITEM_ID, id);
            RecipeViewFragment fragment = new RecipeViewFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, RecipeViewActivity.class);
            detailIntent.putExtra(RecipeViewFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
