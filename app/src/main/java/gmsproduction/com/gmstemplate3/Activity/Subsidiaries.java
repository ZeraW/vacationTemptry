package gmsproduction.com.gmstemplate3.Activity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.Locale;

import gmsproduction.com.gmstemplate3.R;

public class Subsidiaries extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout mDemoSlider;

    ArrayList<Integer> ImgArray;

    String en_ActionBar_Ttile = "Subsidiaries";

    int language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider_subsidiaries);
        mDemoSlider = (SliderLayout) findViewById(R.id.SubsidSilder);
        ImgArray = new ArrayList<>();

        language = getIdLANG();

        if(language==1){
            setTitle("الشركات الفرعية");

        }else{
            setTitle(en_ActionBar_Ttile);

        }

        ImgArray.add(R.drawable.gms);
        ImgArray.add(R.drawable.mr_music);
        ImgArray.add(R.drawable.naghmaty);
        ImgArray.add(R.drawable.teleone);

        loadIMG(ImgArray);


    }
    public void loadIMG(ArrayList<Integer> arryListy){
        for (int i = 0; i < arryListy.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(arryListy.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit.CenterCrop)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putInt("currentImg", i);

            mDemoSlider.addSlider(textSliderView);
        }
        // you can change animasi, time page and anythink.. read more on github
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
/*
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
*/
        //mDemoSlider.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator_duck2));



/*
                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
*/
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
