package com.digitalsigma.vacationcruise.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.digitalsigma.vacationcruise.Activity.Cruises.EndlessRecyclerViewScrollListener;
import com.digitalsigma.vacationcruise.Adapters.ServiceAdapter;
import com.digitalsigma.vacationcruise.Models.MainModel;
import com.digitalsigma.vacationcruise.Models.Utils;
import com.digitalsigma.vacationcruise.R;

public class ServiceActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ServiceAdapter mAdapter;
    private ArrayList<MainModel> modelArrayList;
    private ArrayList<String> modelArrayList2;
    private RequestQueue mRequestQueue;
    private String data_description, data_name, data_logo, images, departure, itinerary;
    int language = 1;
    ProgressDialog pDialog;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_service);


        language = getIdLANG();
        modelArrayList = new ArrayList<>();
        //modelArrayList2 = new ArrayList<>();

        mAdapter = new ServiceAdapter(ServiceActivity.this, modelArrayList);
        //recycler View horizon orientation
        mRecyclerView = findViewById(R.id.Recycler_Service);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiceActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                parseJSON(page+1);
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);


        parseJSON(1);

    }

    private void parseJSON(int page) {

        String url = Utils.BASE_URL_SERVICES+page;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray dataArray = object.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject objectdata = dataArray.getJSONObject(i);

                        String id = objectdata.getString("id");
                        data_name = objectdata.getString("title");
                        data_description = objectdata.getString("description");
                        itinerary = objectdata.getString("itinerary");
                        departure = objectdata.getString("destination");
                        data_logo = Utils.BASE_URL + objectdata.getString("header_img");

                        JSONArray imgArray = objectdata.getJSONArray("gallery");
                        modelArrayList2 = new ArrayList<>();
                        for (int j = 0; j < imgArray.length(); j++) {
                            JSONObject imgObject = imgArray.getJSONObject(j);
                            images = Utils.BASE_URL + imgObject.getString("path");
                            modelArrayList2.add(images);
                        }



                        modelArrayList.add(new MainModel(id,data_name, data_description, itinerary, data_logo, departure, modelArrayList2));
                    }
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ServiceActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ServiceActivity.this);
                }
                builder.setTitle("No Internet")
                        .setMessage("Please check your internet connection and try again")
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                parseJSON(1);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


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
        mRequestQueue = Volley.newRequestQueue(ServiceActivity.this);
        mRequestQueue.add(request);
    }
}
