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

import edu.boun.cmpe451.group2.android.R;
import edu.boun.cmpe451.group2.android.SemanticTagActivity;
import edu.boun.cmpe451.group2.android.ingredient.IngredientAddActivity;

import static android.R.id.text1;

/**
 * A recipe-add screen
 */
public class RecipeAddActivity extends AppCompatActivity {

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
    private Button recipeAddButton;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_add);

        recipeName = (EditText) findViewById(R.id.recipe_add_name_text);

        recipeDescription = (EditText) findViewById(R.id.recipe_add_description_text);

        Button ingredientAddButton = (Button) findViewById(R.id.recipe_add_ingredient_button);
        ingredientAddButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), IngredientAddActivity.class));
            }
        });

        Button semanticTtagAddButton =(Button) findViewById(R.id.semantic_tag_add_button);
        semanticTtagAddButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SemanticTagActivity.class));
            }
        });

        recipeAddButton = (Button) findViewById(R.id.recipe_add_button);


        final  TextView tv = (TextView) findViewById(R.id.textView2);

        String editText=getIntent().getStringExtra("NAME");
        String editText2=getIntent().getStringExtra("CLASSS");



        String str="NAME : "+editText+"\nCLASSS : "+editText2;
        tv.setText(str);
        setContentView(tv);


    }
}