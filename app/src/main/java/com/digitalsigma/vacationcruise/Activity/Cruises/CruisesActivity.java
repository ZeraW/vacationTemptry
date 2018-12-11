package com.digitalsigma.vacationcruise.Activity.Cruises;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.Activity.BaseActivity;
import com.digitalsigma.vacationcruise.R;

import java.util.ArrayList;

public class CruisesActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mInfo,mGallery, mForm;
    private int lang;
    private ViewPager mViewPager;
    private PagerViewAdapter mPagerViewAdapter;
    private ArrayList<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruise);
        lang = 1;
        Init();

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

    public ArrayList<String> getmList() {
        return mList;
    }

    private void changeTabs(int position) {
        switch (position) {
            case 0:
                mInfo.setTextColor(getResources().getColor(R.color.textTabBright));
                mInfo.setTextSize(22);
                mInfo.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mGallery.setTextColor(getResources().getColor(R.color.textTabLight));
                mGallery.setTextSize(16);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mForm.setTextColor(getResources().getColor(R.color.textTabLight));
                mForm.setTextSize(16);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                break;
            case 1:

                mGallery.setTextColor(getResources().getColor(R.color.textTabBright));
                mGallery.setTextSize(22);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mInfo.setTextColor(getResources().getColor(R.color.textTabLight));
                mInfo.setTextSize(16);
                mInfo.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mForm.setTextColor(getResources().getColor(R.color.textTabLight));
                mForm.setTextSize(16);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                break;


            case 2:
                mForm.setTextColor(getResources().getColor(R.color.textTabBright));
                mForm.setTextSize(22);
                mForm.setBackgroundColor(getResources().getColor(R.color.textTabLight));

                mGallery.setTextColor(getResources().getColor(R.color.textTabLight));
                mGallery.setTextSize(16);
                mGallery.setBackgroundColor(getResources().getColor(R.color.textTabBright));

                mInfo.setTextColor(getResources().getColor(R.color.textTabLight));
                mInfo.setTextSize(16);
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
        mInfo.setOnClickListener(this);
        mForm.setOnClickListener(this);
        mGallery.setOnClickListener(this);

    }
}
