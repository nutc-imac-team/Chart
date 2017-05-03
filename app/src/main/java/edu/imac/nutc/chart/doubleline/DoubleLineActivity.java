package edu.imac.nutc.chart.doubleline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.imac.nutc.chart.R;

/**
 * Created by cheng on 2017/4/11.
 */

public class DoubleLineActivity extends AppCompatActivity {
    TextView upperTextview;
    TextView lowerTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_line);
        upperTextview= (TextView) findViewById(R.id.double_line_upper_current_num_textview);
        lowerTextview= (TextView) findViewById(R.id.double_line_lower_current_num_textview);
        upperTextview.setText("9");
        lowerTextview.setText("26");
    }
}
