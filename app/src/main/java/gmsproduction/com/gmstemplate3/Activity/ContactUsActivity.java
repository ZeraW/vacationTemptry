package gmsproduction.com.gmstemplate3.Activity;

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

import gmsproduction.com.gmstemplate3.Adapters.ContactUsAdapter;
import gmsproduction.com.gmstemplate3.R;

public class ContactUsActivity extends BaseActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private RecyclerView mRecyclerView;
    private ContactUsAdapter mAdapter;
    private ArrayList<String> modelArrayList;
    private ArrayList<Integer> modelArrayList2;
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
        modelArrayList2 = new ArrayList<>();

        modelArrayList.add("(66) Al-Mehwar Al-Markazy -district 10- neighborhood (2) - 6th October 4th Floor (4)-Giza-Egypt.");
        modelArrayList.add("(+202) 36864622");
        modelArrayList.add("GMS Group");
        modelArrayList.add("info@gms-group.company");
        modelArrayList.add("www.gms-group.company");
/*
        modelArrayList.add("");
*/

        modelArrayList2.add(R.drawable.google_maps);
        modelArrayList2.add(R.drawable.phone_receiver);
        modelArrayList2.add(R.drawable.facebook);
        modelArrayList2.add(R.drawable.email);
        modelArrayList2.add(R.drawable.grid_world);
/*
        modelArrayList2.add(R.drawable.youtube_logo);
*/

        mAdapter = new ContactUsAdapter(ContactUsActivity.this, modelArrayList,modelArrayList2);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(29.960060974539, 30.918692350388);
        mMap.addMarker(new MarkerOptions().position(sydney).title("GMS Group"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.7f));

    }

}
