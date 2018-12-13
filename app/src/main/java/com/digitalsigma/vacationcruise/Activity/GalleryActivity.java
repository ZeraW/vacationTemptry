package com.digitalsigma.vacationcruise.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.digitalsigma.vacationcruise.Adapters.GalleryAdapter;
import com.digitalsigma.vacationcruise.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GalleryActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private ArrayList<String> modelArrayList2;
    private RequestQueue mRequestQueue;
    private  String data_description, data_name, data_logo , images;
    int language = 1;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_gallery);

        language = getIdLANG();

        if(language==1){
            setTitle("المعرض");
        }else{
            setTitle("Gallery");
        }

        //recycler View horizon orientation
        mRecyclerView = findViewById(R.id.Recycler_Galleryz);
        LinearLayoutManager LayoutManagaer = new GridLayoutManager(GalleryActivity.this, 3);
        mRecyclerView.setLayoutManager(LayoutManagaer);
          parseJSON(language);
    }


    private void parseJSON(final int x) {

        modelArrayList2 = new ArrayList<>();

        String url = "http://gms-sms.com:89/gmsred/api/galleries";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject objectdata = dataArray.getJSONObject(i);

                        data_logo = "http://gms-sms.com:89" + objectdata.getString("image");

                        modelArrayList2.add(data_logo);

                    }
                    mAdapter = new GalleryAdapter(GalleryActivity.this,modelArrayList2,x);
                    mRecyclerView.setAdapter(mAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(GalleryActivity.this);
        mRequestQueue.add(request);
    }

}
