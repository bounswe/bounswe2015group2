package edu.boun.cmpe451.group2.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.boun.cmpe451.group2.android.recipe.RecipeAddActivity;

public class SemanticTagActivity extends Activity {

    public static final String EXTRA_TAG_NAME = "tag.name";
    public static final String EXTRA_TAG_CLASS = "tag.class";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semantic_tag);

        Button button = (Button) findViewById(R.id.button);

        final EditText editTextName = (EditText) findViewById(R.id.editText);
        final EditText editTextClass = (EditText) findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String editText = editTextName.getText().toString();
                String editText2 = editTextClass.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(EXTRA_TAG_NAME, editText);
                intent.putExtra(EXTRA_TAG_CLASS, editText2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
