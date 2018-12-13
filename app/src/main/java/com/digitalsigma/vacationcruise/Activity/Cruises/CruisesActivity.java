package com.digitalsigma.vacationcruise.Activity.Cruises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.Activity.BaseActivity;
import com.digitalsigma.vacationcruise.Models.Utils;
import com.digitalsigma.vacationcruise.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CruisesActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mInfo, mGallery, mForm;
    private int lang;
    private ViewPager mViewPager;
    private PagerViewAdapter mPagerViewAdapter;
    private ArrayList<String> mImgList;
    private ImageView backBtn,bannerImg;

    private String id, name, description, itinerary, logo, departure;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise);
        lang = 1;
        Init();
        getIntet();

        Picasso.with(this).load(logo).centerCrop().fit().into(bannerImg);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeTabs(int position) {
        switch (position) {
            case 0:
                mInfo.setTextColor(getResources().getColor(R.color.textTabBright));
                mInfo.setTextSize(17);
                mInfo.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mGallery.setTextColor(getResources().getColor(R.color.textTabLight));
                mGallery.setTextSize(14);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mForm.setTextColor(getResources().getColor(R.color.textTabLight));
                mForm.setTextSize(14);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                break;
            case 1:

                mGallery.setTextColor(getResources().getColor(R.color.textTabBright));
                mGallery.setTextSize(17);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mInfo.setTextColor(getResources().getColor(R.color.textTabLight));
                mInfo.setTextSize(14);
                mInfo.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mForm.setTextColor(getResources().getColor(R.color.textTabLight));
                mForm.setTextSize(14);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                break;


            case 2:
                mForm.setTextColor(getResources().getColor(R.color.textTabBright));
                mForm.setTextSize(17);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mGallery.setTextColor(getResources().getColor(R.color.textTabLight));
                mGallery.setTextSize(14);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mInfo.setTextColor(getResources().getColor(R.color.textTabLight));
                mInfo.setTextSize(14);
                mInfo.setBackgroundColor(getResources().getColor(R.color.textTabBright));
                break;


        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.mProfile:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.mGallery:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.mUsers:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.cruise_back:
                finish();
                break;

        }

    }

    private void Init() {
        mInfo = findViewById(R.id.mProfile);
        mForm = findViewById(R.id.mUsers);
        mGallery = findViewById(R.id.mGallery);
        mViewPager = findViewById(R.id.mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerViewAdapter);
        backBtn = findViewById(R.id.cruise_back);
        bannerImg = findViewById(R.id.Img);
        backBtn.setOnClickListener(this);
        mInfo.setOnClickListener(this);
        mForm.setOnClickListener(this);
        mGallery.setOnClickListener(this);

    }

    private void getIntet() {
        Intent extra = getIntent();

        id = extra.getStringExtra("id");
        name = extra.getStringExtra("name");
        logo = extra.getStringExtra("image");
        description = extra.getStringExtra("desc");
        itinerary = extra.getStringExtra("itinerary");
        departure = extra.getStringExtra("departure");
        mImgList = extra.getStringArrayListExtra("imgArray");

    }


    public ArrayList<String> getmImgList() {
        return mImgList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItinerary() {
        return itinerary;
    }

    public String getLogo() {
        return logo;
    }

    public String getDeparture() {
        return departure;
    }
}
