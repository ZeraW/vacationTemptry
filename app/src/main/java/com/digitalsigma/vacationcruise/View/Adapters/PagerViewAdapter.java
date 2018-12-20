package com.digitalsigma.vacationcruise.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.digitalsigma.vacationcruise.View.Fragment.GalleryFragment;
import com.digitalsigma.vacationcruise.View.Fragment.InfoFragment;
import com.digitalsigma.vacationcruise.View.Fragment.BookingFragment;


public class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment();
            case 1:
                return new GalleryFragment();
            case 2:
                return new BookingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
