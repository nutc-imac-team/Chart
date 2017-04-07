package edu.imac.nutc.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class BarActivity extends AppCompatActivity {
    TextView current_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        current_num= (TextView) findViewById(R.id.current_num);
        current_num.setText("80.8");
    }
}
