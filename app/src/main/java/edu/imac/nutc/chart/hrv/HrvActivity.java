package edu.imac.nutc.chart.hrv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.imac.nutc.chart.model.API;
import edu.imac.nutc.chart.R;
import edu.imac.nutc.chart.model.SetWeek;

public class HrvActivity extends AppCompatActivity{
    protected String TAG = HrvActivity.class.getName();
    @Bind(R.id.hrv_upper_current_num_textview)
    TextView upperTextView;
    @Bind(R.id.hrv_lower_current_num_textview)
    TextView lowerTextView;
    @Bind(R.id.hrv_view)
    HrvChart hrvChart;
    private API api;
    private String network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        network=getIntent().getStringExtra("network");
        api=new API(HrvActivity.this);
        api.getHRV(network);
        api.setOnHRVFinish(getHRVFinish);
    }
    API.GetHRVFinish getHRVFinish=new API.GetHRVFinish() {
        @Override
        public void finish(String response) throws JSONException {
            String [] str= SetWeek.getWeek();
            float [] morning=new float[7];
            float [] evening=new float[7];
            JSONObject jsonObject=new JSONObject(response);
            for (int i=0;i<str.length;i++){
                JSONObject jsonObject1=jsonObject.getJSONObject(str[i]);
                BigDecimal morningHrv = new BigDecimal(Float.valueOf(jsonObject1.get("morningHrv").toString())/100);
                morning[i]=morningHrv.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();

                BigDecimal eveningHrv = new BigDecimal(Float.valueOf(jsonObject1.get("eveningHrv").toString())/100);
                evening[i]=eveningHrv.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();

            }
            hrvChart.setLineData(morning);
            hrvChart.setGraphicData(evening);
            hrvChart.invalidate();
            upperTextView.setText(morning[6]+"");
            lowerTextView.setText(evening[6]+"");
        }
    };
}
