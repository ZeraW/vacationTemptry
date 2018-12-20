package com.digitalsigma.vacationcruise.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.digitalsigma.vacationcruise.Model.ViewPageModel;
import com.digitalsigma.vacationcruise.R;

/**
 * Created by Hima on 4/19/2018.
 */

public class MainSplashAdapter extends PagerAdapter {
    Context mContext ;
    LayoutInflater layoutInflater;
    public ArrayList<ViewPageModel> mList;

    public MainSplashAdapter(Context mContext, ArrayList<ViewPageModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.splash_viewpager,container,false);
        ImageView background = view.findViewById(R.id.viewpager_background);
        ImageView icons = view.findViewById(R.id.viewpager_icon);
        TextView titles = view.findViewById(R.id.viewpager_title);
        TextView Des = view.findViewById(R.id.viewpager_Desc);




        Picasso.with(mContext).load(mList.get(position).getBackgrounds()).fit().centerCrop().into(background);
        //Picasso.with(mContext).load(mList.get(position).getIcons()).fit().centerInside().into(icons);
        //titles.setText(mList.get(position).getTitles());
       // Des.setText(mList.get(position).getDescriptions());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout) object);
    }
}
