package edu.imac.nutc.chart.hrv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.imac.nutc.chart.R;

public class HrvActivity extends AppCompatActivity {
    protected String TAG = HrvActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv);
        final HrvView hrvView = (HrvView) findViewById(R.id.hrv_view);
    }
}
