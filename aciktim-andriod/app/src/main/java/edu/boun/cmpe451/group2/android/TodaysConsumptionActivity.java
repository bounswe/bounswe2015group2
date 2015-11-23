package edu.boun.cmpe451.group2.android;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ugur_tombul on 22.11.2015.
 */
public class TodaysConsumptionActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_consumptions);
    }
}
