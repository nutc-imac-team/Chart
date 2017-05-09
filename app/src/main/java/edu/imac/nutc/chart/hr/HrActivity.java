package edu.imac.nutc.chart.hr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.imac.nutc.chart.R;

import edu.imac.nutc.chart.model.API;
import edu.imac.nutc.chart.model.SetWeek;

/**
 * Created by cheng on 2017/5/2.
 */

public class HrActivity extends AppCompatActivity {
    @Bind(R.id.hr_upper_current_num_textview)
    TextView upperTextView;
    @Bind(R.id.hr_median_current_num_textview)
    TextView medianTextView;
    @Bind(R.id.hr_lower_current_num_textview)
    TextView lowerTextView;
    @Bind(R.id.hr_view)
    HrChart hrChart;
    API api;
    private String network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        network=getIntent().getStringExtra("network");
        api=new API(HrActivity.this);
        api.getHR(network);
        api.setOnHRFinish(getHRFinish);
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
            hrChart.invalidate();
            upperTextView.setText(max[6]+"");
            medianTextView.setText(avg[6]+"");
            lowerTextView.setText(min[6]+"");
        }
    };
}
