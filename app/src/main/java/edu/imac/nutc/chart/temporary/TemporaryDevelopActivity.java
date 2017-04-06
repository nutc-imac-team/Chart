package edu.imac.nutc.chart.temporary;

import android.os.Bundle;

import edu.imac.nutc.chart.MainActivity;


/**
 * Created by Ameng on 2015/12/14.
 */
public class TemporaryDevelopActivity extends SeparateDeveloperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity
        addActivityButton(MainActivity.class, null);
    }
}
