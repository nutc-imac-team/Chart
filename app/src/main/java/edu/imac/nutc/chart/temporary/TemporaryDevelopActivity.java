package edu.imac.nutc.chart.temporary;

import android.os.Bundle;

import edu.imac.nutc.chart.doubleline.DoubleLineActivity;
import edu.imac.nutc.chart.hr.HrActivity;
import edu.imac.nutc.chart.hrv.HrvActivity;
import edu.imac.nutc.chart.main.MainActivity;
import edu.imac.nutc.chart.total.BarActivity;
import edu.imac.nutc.chart.BreathingRateActivity;


/**
 * Creatgied by Ameng on 2015/12/14.
 */
public class TemporaryDevelopActivity extends SeparateDeveloperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity
        addActivityButton(MainActivity.class, null);
        addActivityButton(BarActivity.class, null);
        addActivityButton(DoubleLineActivity.class, null);
        addActivityButton(HrvActivity.class, null);
        addActivityButton(HrActivity.class,null);
        addActivityButton(BreathingRateActivity.class,null);
    }
}
