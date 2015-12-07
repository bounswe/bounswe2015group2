package edu.boun.cmpe451.group2.android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

import edu.boun.cmpe451.group2.android.dummy.DummyContent;
import edu.boun.cmpe451.group2.android.recipe.RecipeAddActivity;

public class SemanticTagActivity extends AppCompatActivity {



    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semantic_tag);
        arrayList = new ArrayList();
        ListView listView = (ListView) findViewById(R.id.listView2);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, arrayList);
        listView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.editText);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                arrayList.add(newItem);
                adapter.notifyDataSetChanged();


            }


        });

        Intent i = new Intent(this, RecipeAddActivity.class);
        startActivityForResult(i, 1);
        onBackPressed();
        
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SemanticTagActivity.class);
        startActivity(intent);
    }


}
