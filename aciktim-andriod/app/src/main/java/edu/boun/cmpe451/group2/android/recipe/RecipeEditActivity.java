package edu.boun.cmpe451.group2.android.recipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.boun.cmpe451.group2.android.R;

/**
 * A recipe-edit screen
 */
public class RecipeEditActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.

    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    */

    // UI references.
    private EditText recipeName;
    private EditText recipeDescription;
    private EditText ingredientName;
    private EditText ingredientQuantity;
    private Button recipeEditButton;
    private Button ingredientAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);

        recipeName = (EditText) findViewById(R.id.recipe_edit_name_text);

        recipeDescription = (EditText) findViewById(R.id.recipe_edit_description_text);

        ingredientName = (EditText) findViewById(R.id.recipe_edit_ingredient_text);
        ingredientQuantity  = (EditText) findViewById(R.id.recipe_edit_ingredient_quantity);
        ingredientAddButton = (Button) findViewById(R.id.recipe_add_ingredient_button);

        recipeEditButton = (Button) findViewById(R.id.recipe_edit_button);

    }
}