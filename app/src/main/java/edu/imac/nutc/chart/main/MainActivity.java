package edu.imac.nutc.chart.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.imac.nutc.chart.R;
import edu.imac.nutc.chart.model.API;

public class MainActivity extends AppCompatActivity {
    SharedPreferences.Editor shareEditor;
    SharedPreferences shareReader;
    @Bind(R.id.network_editText)
    EditText network_editText;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        api = new API(this);
        shareReader = getSharedPreferences("Chart", 0);
        shareEditor = shareReader.edit();
        network_editText.setText(shareReader.getString("network", ""));
    }

    @OnClick(R.id.network_button)
    void networkOnclick() {
        api.getLogin(network_editText.getText().toString());
        api.setOnLoginFinish(loginFinish);

    }

    API.GetLoginFinish loginFinish = new API.GetLoginFinish() {
        @Override
        public void finish(String response) throws JSONException {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.get("message").equals("Success")) {
                shareEditor.putString("network", jsonObject.get("network").toString()).commit();
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("network", jsonObject.get("network").toString());
                startActivity(intent);
            }
        }
    };
}
