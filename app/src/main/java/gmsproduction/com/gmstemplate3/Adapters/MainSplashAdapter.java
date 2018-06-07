package gmsproduction.com.gmstemplate3.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import gmsproduction.com.gmstemplate3.R;

/**
 * Created by Hima on 4/19/2018.
 */

public class MainSplashAdapter extends PagerAdapter {
    Context mContext ;
    LayoutInflater layoutInflater;
    String title = "GMS GROUP";
    String Desc_1 = "Group of companies have their own \n" +
            "Investment field in information \n" +
            "technology , production.";

    String Desc_2 = "Mobile application , Website , Digital \n" +
            "content , Plus trade electronic , Networking \n" +
            "Software , Marketing \n" +
            "And internet services .";
    //array of icons
    public int [] ICONZ = {
            R.drawable.gms_group1,
            R.drawable.bnbn_01,
            R.drawable.p_01
    } ;

    //array of backgound
    public int [] BACKGROUNDZ = {
            R.drawable.android_mobile_1,
            R.drawable.android_mobile_2,
            R.drawable.android_mobile_2
    } ;
    //array of Titles
    public String [] TITLEZ = {
            "",
            title,
            title
    } ;
    //array of Description
    public String [] Descz = {
            "",
            Desc_1,
            Desc_2
    } ;

    public MainSplashAdapter(Context mContext) {
        this.mContext = mContext;
    }




    @Override
    public int getCount() {
        return ICONZ.length;
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


        Picasso.with(mContext).load(BACKGROUNDZ[position]).fit().centerCrop().into(background);
        Picasso.with(mContext).load(ICONZ[position]).fit().centerInside().into(icons);
        titles.setText(TITLEZ[position]);
        Des.setText(Descz[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout) object);
    }
}
