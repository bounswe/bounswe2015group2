package edu.boun.cmpe451.group2.android;

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

public class SemanticTagActivity extends AppCompatActivity {



    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semantic_tag);
        ListView listView=(ListView)findViewById(R.id.listView2);
        editText=(EditText)findViewById(R.id.editText);
        Button button2 =(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

         public void onClick(View v)
         {
           String newItem=editText.getText().toString();
           arrayList.add(newItem);
           adapter.notifyDataSetChanged();
         }

        });
        
    } }
