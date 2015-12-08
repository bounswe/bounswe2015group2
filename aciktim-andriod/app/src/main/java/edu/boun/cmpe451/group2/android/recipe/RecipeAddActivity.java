package edu.boun.cmpe451.group2.android.recipe;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.SemanticTagActivity;
import edu.boun.cmpe451.group2.android.api.SemanticTag;
import edu.boun.cmpe451.group2.android.ingredient.IngredientAddActivity;

import static android.R.id.text1;

/**
 * A recipe-add screen
 */
public class RecipeAddActivity extends AppCompatActivity {

    private static final int REQ_SEMANTIC_TAG_ACTIVITY = 999;
    /**
     * Id to identity READ_CONTACTS permission request.
     * <p/>
     * private static final int REQUEST_READ_CONTACTS = 0;
     * <p/>
     * /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     * <p/>
     * private static final String[] DUMMY_CREDENTIALS = new String[]{
     * "foo@example.com:hello", "bar@example.com:world"
     * };
     */

    // UI references.
    private EditText recipeName;
    private EditText recipeDescription;
    private Button recipeAddButton;
    private ListView listView;

    List<SemanticTag> semanticTags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_add);

        recipeName = (EditText) findViewById(R.id.recipe_add_name_text);
        listView = (ListView) findViewById(R.id.listView);
        SemanticTagAdapter semanticTagAdapter = new SemanticTagAdapter(this, semanticTags);
        listView.setAdapter(semanticTagAdapter);

        recipeDescription = (EditText) findViewById(R.id.recipe_add_description_text);
        Button ingredientAddButton = (Button) findViewById(R.id.recipe_add_ingredient_button);
        ingredientAddButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IngredientAddActivity.class));
            }
        });

        Button semanticTagAddButton = (Button) findViewById(R.id.semantic_tag_add_button);
        semanticTagAddButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), SemanticTagActivity.class), REQ_SEMANTIC_TAG_ACTIVITY);
            }
        });

        recipeAddButton = (Button) findViewById(R.id.recipe_add_button);

        // my code start here


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_SEMANTIC_TAG_ACTIVITY && resultCode == RESULT_OK) {
            String semanticTag = data.getExtras().getString(SemanticTagActivity.EXTRA_TAG_NAME);
            String semanticTagClass = data.getExtras().getString(SemanticTagActivity.EXTRA_TAG_CLASS);
            SemanticTag tempSemanticTag = new SemanticTag();
            tempSemanticTag.setTagName(semanticTag);
            tempSemanticTag.setTagClass(semanticTagClass);
            semanticTags.add(tempSemanticTag);
            ((SemanticTagAdapter)listView.getAdapter()).notifyDataSetChanged();
        }
    }
}