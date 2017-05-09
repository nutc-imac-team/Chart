package edu.imac.nutc.chart.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.imac.nutc.chart.R;
import edu.imac.nutc.chart.br.BreathingRateActivity;
import edu.imac.nutc.chart.hr.HrActivity;
import edu.imac.nutc.chart.hrv.HrvActivity;

/**
 * Created by cheng on 2017/5/5.
 */

public class MenuActivity extends AppCompatActivity{
    private String network;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        network=getIntent().getStringExtra("network");
    }
    @OnClick(R.id.hr_button)
    void hr_button(){
        Intent intent=new Intent(MenuActivity.this, HrActivity.class);
        intent.putExtra("network",network);
        startActivity(intent);
    }
    @OnClick(R.id.hrv_button)
    void hrv_button(){
        Intent intent=new Intent(MenuActivity.this, HrvActivity.class);
        intent.putExtra("network",network);
        startActivity(intent);
    }
    @OnClick(R.id.br_button)
    void br_button(){
        Intent intent=new Intent(MenuActivity.this, BreathingRateActivity.class);
        intent.putExtra("network",network);
        startActivity(intent);
    }
}
