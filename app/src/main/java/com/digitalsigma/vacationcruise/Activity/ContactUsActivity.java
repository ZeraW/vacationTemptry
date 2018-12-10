package com.digitalsigma.vacationcruise.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import com.digitalsigma.vacationcruise.Adapters.ContactUsAdapter;
import com.digitalsigma.vacationcruise.Models.ContactUsModel;
import com.digitalsigma.vacationcruise.R;

public class ContactUsActivity extends BaseActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private RecyclerView mRecyclerView;
    private ContactUsAdapter mAdapter;
    private ArrayList<ContactUsModel>modelArrayList;
    int language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_contactus_maps);

        language = getIdLANG();

        if(language==1){
            setTitle("تواصل معنا");

        }else{
            setTitle("Contact US");

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mRecyclerView = findViewById(R.id.Recycler_ContactUs);
        LinearLayoutManager LayoutManagaer = new LinearLayoutManager(ContactUsActivity.this);
        mRecyclerView.setLayoutManager(LayoutManagaer);
        addData();

    }

    private void addData(){

        modelArrayList = new ArrayList<>();

        modelArrayList.add(new ContactUsModel("66 Al Mehwar Al Markazi, District 10 - 6th of October, Egypt.",R.drawable.google_maps));
        modelArrayList.add(new ContactUsModel("(+202) 36864622",R.drawable.phone_receiver));
        modelArrayList.add(new ContactUsModel("Digital Sigma",R.drawable.facebook));
        modelArrayList.add(new ContactUsModel("contact@digitalsigma.io",R.drawable.email));
        modelArrayList.add(new ContactUsModel("https://digitalsigma.io",R.drawable.grid_world));

        mAdapter = new ContactUsAdapter(ContactUsActivity.this, modelArrayList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(29.960060974539, 30.918692350388);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Digital Sigma"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.7f));

    }

}
