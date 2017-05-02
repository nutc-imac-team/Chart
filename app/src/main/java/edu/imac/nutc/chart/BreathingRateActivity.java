package edu.imac.nutc.chart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 2017/5/1.
 */

public class BreathingRateActivity extends AppCompatActivity {
    int[] maxData = {40 , 25, 40, 30, 25, 50, 24};
    int[] minData = {5 , 0, 5, 5, 5, 5, 5};
    BreathingWeekendView breathingWeekendView;
    TextView avgHighBmp;
    TextView avgLowBmp;
    TextView avgBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathing_rate);
         breathingWeekendView= (BreathingWeekendView) findViewById(R.id.breathing_weekend);
        breathingWeekendView.setMaxData(maxData);
        breathingWeekendView.setMinData(minData);
        breathingWeekendView.invalidate();
        findView();
    }

    private void findView() {
        avgHighBmp= (TextView) findViewById(R.id.breathing_rate_avg_high_num);
        avgLowBmp= (TextView) findViewById(R.id.breathing_rate_avg_bpm_num);
        avgBmp= (TextView) findViewById(R.id.breathing_rate_avg_low_num);


    }
}
