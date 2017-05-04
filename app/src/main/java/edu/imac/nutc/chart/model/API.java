package edu.imac.nutc.chart.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheng on 2017/5/2.
 */

public class API {
    private RequestQueue queue;
    private Context context;
    private GetHRVFinish getHRVFinish;
    private GetHRFinish getHRFinish;
    private GetBRFinish getBRFinish;
    public API(Context context){
        this.context=context;
        queue = SingleRequestQueue.getQueue(context);
    }
    public void setOnBRFinish(GetBRFinish getBRFinish) {
        this.getBRFinish = getBRFinish;
    }
    public interface GetBRFinish{
        void finish(String response) throws JSONException;
    }
    public void getBR(final String network) {
        String url = "http://smartbed.honixtech.com:8081/api/getBR";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (getBRFinish != null) {
                            try {
                                getBRFinish.finish(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求错误:", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("network", network);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void setOnHRVFinish(GetHRVFinish getHRVFinish) {
        this.getHRVFinish = getHRVFinish;
    }
    public interface GetHRVFinish{
        void finish(String response) throws JSONException;
    }
    public void getHRV(final String network) {
        String url = "http://smartbed.honixtech.com:8081/api/getHRV";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (getHRVFinish != null) {
                            try {
                                getHRVFinish.finish(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求错误:", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("network", network);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void setOnHRFinish(GetHRFinish getHRFinish) {
        this.getHRFinish = getHRFinish;
    }
    public interface GetHRFinish{
        void finish(String response) throws JSONException;
    }
    public void getHR(final String network) {
        String url = "http://smartbed.honixtech.com:8081/api/getHR";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (getHRFinish != null) {
                            try {
                                getHRFinish.finish(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求错误:", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("network", network);
                return params;
            }
        };
        queue.add(stringRequest);
    }




    private static final class SingleRequestQueue {
        private volatile static RequestQueue queue;

        private SingleRequestQueue() {
        }

        private static RequestQueue getQueue(Context context) {
            if (queue == null) {
                synchronized (SingleRequestQueue.class) {
                    if (queue == null) {
                        queue = Volley.newRequestQueue(context);
                    }
                }
            }
            return queue;
        }
    }
}
