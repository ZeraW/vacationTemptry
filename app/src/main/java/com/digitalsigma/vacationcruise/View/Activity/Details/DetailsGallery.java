package com.digitalsigma.vacationcruise.View.Activity.Details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.digitalsigma.vacationcruise.View.Activity.BaseActivity;
import com.digitalsigma.vacationcruise.Model.Gallery;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.Utils.Const;

import java.util.ArrayList;



public class DetailsGallery extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    SliderLayout mDemoSlider;

    ArrayList<Gallery> modelServices;
    private int lolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_gallery);

        mDemoSlider = (SliderLayout) findViewById(R.id.GalleriesSilder);

        /*modelServices = new ArrayList<>();*/
        Intent k = getIntent();

        Bundle getextra = getIntent().getExtras();
        modelServices = (ArrayList<Gallery>) getextra.getSerializable("ArraylistOFimg");

        //modelServices = k.getStringArrayListExtra("ArraylistOFimg");
        lolo = k.getIntExtra("imgnum",0);
        Log.e("duuuuu",modelServices.toString());
        setTitle(k.getStringExtra("ToolbarTitle"));

        loadIMG(modelServices);

    }
    public void loadIMG(ArrayList<Gallery> arryListy){
        for (int i = 0; i < arryListy.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(Const.BASE_IMG_URL + arryListy.get(i).getPath())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putInt("currentImg", i);

            mDemoSlider.addSlider(textSliderView);
        }
        // you can change animasi, time page and anythink.. read more on github
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
/*
                    mDemoSlider.setCustomAnimation(new DescriptionAnimation());
*/
        mDemoSlider.setCurrentPosition(lolo);
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
