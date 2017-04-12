package edu.imac.nutc.chart.total;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.imac.nutc.chart.R;


public class BarActivity extends AppCompatActivity {
    TextView current_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        current_num= (TextView) findViewById(R.id.bar_current_num_textview);
        current_num.setText("80.8");
    }
}
