package com.digitalsigma.vacationcruise.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.digitalsigma.vacationcruise.Adapters.ProductAdapter;
import com.digitalsigma.vacationcruise.Models.ProductsModel;
import com.digitalsigma.vacationcruise.Models.Utils;
import com.digitalsigma.vacationcruise.R;

public class ProductActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private ArrayList<ProductsModel> modelArrayList;
    private ArrayList<String> modelArrayList2;
    private RequestQueue mRequestQueue;
    private  String data_description, data_name, data_logo , images , url;
    int language = 1;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_product);

        language = getIdLANG();

        if(language==1){
            setTitle("المنتجات");
        }else{
            setTitle("Products");
        }

        //recycler View horizon orientation
        mRecyclerView = findViewById(R.id.Recycler_Product);
        LinearLayoutManager LayoutManagaer = new GridLayoutManager(ProductActivity.this, 2);
        mRecyclerView.setLayoutManager(LayoutManagaer);


        parseJSON(language);

    }

    private void parseJSON(final int x) {

        modelArrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Utils.BASE_URL_PRODUCTS,  null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONresponse(response,x);

/*
                save that string in dataBase

                response.toString();
*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ProductActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ProductActivity.this);
                }
                builder.setTitle("No Internet")
                        .setMessage("Please check your internet connection and try again")
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                parseJSON(language);
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
        mRequestQueue = Volley.newRequestQueue(ProductActivity.this);
        mRequestQueue.add(request);
    }

    private void JSONresponse(JSONObject response,int x){
        try {
            JSONArray dataArray = response.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject objectdata = dataArray.getJSONObject(i);
                int data_id = objectdata.getInt("id");

                //String data_description, data_name, data_logo , images;
                if (x == 1) {
                    data_name = objectdata.getString("ar_name");
                    data_description = objectdata.getString("ar_description");
                } else {
                    data_name = objectdata.getString("en_name");
                    data_description = objectdata.getString("en_description");
                }

                data_logo = Utils.BASE_URL + objectdata.getString("logo");
                url = objectdata.getString("link");

                modelArrayList.add(new ProductsModel(data_name, data_description, data_logo,url));

            }
            mAdapter = new ProductAdapter(ProductActivity.this, modelArrayList,x);
            mRecyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
