package edu.boun.cmpe451.group2.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.boun.cmpe451.group2.android.dummy.DummyContent;

public class SemanticTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semantic_tag);
        ListView listView =(ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getApplicationContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));
    }
}
