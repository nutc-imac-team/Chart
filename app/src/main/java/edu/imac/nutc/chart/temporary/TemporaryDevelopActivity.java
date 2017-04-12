package edu.imac.nutc.chart.temporary;

import android.os.Bundle;

import edu.imac.nutc.chart.BarActivity;
import edu.imac.nutc.chart.BarChart;
import edu.imac.nutc.chart.DoubleLineActivity;
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
        addActivityButton(BarActivity.class, null);
        addActivityButton(DoubleLineActivity.class, null);
    }
}
