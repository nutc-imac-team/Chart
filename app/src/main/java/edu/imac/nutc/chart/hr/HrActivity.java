package edu.imac.nutc.chart.hr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import edu.imac.nutc.chart.R;

import edu.imac.nutc.chart.model.API;
import edu.imac.nutc.chart.model.SetWeek;

/**
 * Created by cheng on 2017/5/2.
 */

public class HrActivity extends AppCompatActivity {
    TextView upperTextView;
    TextView medianTextView;
    TextView lowerTextView;
    HrChart hrChart;
    API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
        init();
    }
    private void init(){
        upperTextView = (TextView) findViewById(R.id.hr_upper_current_num_textview);
        medianTextView = (TextView) findViewById(R.id.hr_median_current_num_textview);
        lowerTextView= (TextView) findViewById(R.id.hr_lower_current_num_textview);
        api=new API(HrActivity.this);
        api.getHR("Cherry");
        api.setOnHRFinish(getHRFinish);
        hrChart= (HrChart) findViewById(R.id.hr_view);
    }

    API.GetHRFinish getHRFinish=new API.GetHRFinish() {
        @Override
        public void finish(String response) throws JSONException {
            String [] str= SetWeek.getWeek();
            int [] max=new int[7];
            int [] avg=new int[7];
            int [] min=new int[7];
            JSONObject jsonObject=new JSONObject(response);
            for (int i=0;i<str.length;i++) {
                JSONObject jsonObject1 = jsonObject.getJSONObject(str[i]);
                max[i]=Integer.valueOf(jsonObject1.get("max").toString());
                min[i]=Integer.valueOf(jsonObject1.get("min").toString());
                if(!jsonObject1.get("avg").toString().equals("0")){
                    String [] sp=jsonObject1.get("avg").toString().split("\\.");
                    avg[i]=Integer.valueOf(sp[0]);
                }else {
                    avg[i]=0;
                }

            }
            hrChart.setLineData(avg);
            hrChart.setMaxGraphicData(max);
            hrChart.setMinGraphicData(min);
            hrChart.setDownload(true);
            hrChart.invalidate();
            upperTextView.setText(max[6]+"");
            medianTextView.setText(avg[6]+"");
            lowerTextView.setText(min[6]+"");
        }
    };
}
