package edu.boun.cmpe451.group2.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.boun.cmpe451.group2.android.recipe.RecipeAddActivity;

public class SemanticTagActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    EditText editText2;

    Button button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semantic_tag);


        editText = (EditText) findViewById(R.id.editText);
        editText2= (EditText) findViewById(R.id.editText2);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

    }

    public void onClick(View v) {
        Intent intent = new Intent(this, RecipeAddActivity.class);
        intent.putExtra("Name", editText.getText().toString());
        intent.putExtra("class", editText2.getText().toString());
        startActivity(intent);
    }

}
