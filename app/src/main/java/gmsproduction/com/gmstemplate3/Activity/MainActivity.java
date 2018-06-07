package gmsproduction.com.gmstemplate3.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import gmsproduction.com.gmstemplate3.Adapters.MainSplashAdapter;
import gmsproduction.com.gmstemplate3.R;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {

    TextView projects,Services,Product,Clients,Gallery,AboutUs,Subsidiaries,ContactUS,LanguageBtn;
    SliderLayout mDemoSlider;
    ArrayList<Integer> ImgArray;
    ViewPager mViewPager;
    MainSplashAdapter adapter ;
    Button btn_upEn, btn_nextAr;
    int mCurrentPage,language,page;
    RelativeLayout splashlayout;
    LinearLayout MainLayout;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localization();
        setContentView(R.layout.activity_main);

        //log in animation
        LinearLayout layout =  findViewById(R.id.Linear_for_animation);
        AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f ) ;
        animation.setFillAfter(true);
        animation.setDuration(1200);

        //apply the animation ( fade In ) to your LAyout
        layout.startAnimation(animation);

        //findViewByID Method
        Initializing();

        //OnClickListenr Method
        btns();

        //splash screen
        mViewPager.setAdapter(adapter);

        //splash Indicator
        ExtensiblePageIndicator extensiblePageIndicator = (ExtensiblePageIndicator) findViewById(R.id.flexibleIndicator);
        extensiblePageIndicator.initViewPager(mViewPager);
        pageSwitcher(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                page = position;
            }

            @Override
            public void onPageSelected(int position) {

                mCurrentPage = position;
                //first page
                if (position == 0) {

                    btn_upEn.setEnabled(true);
                    btn_upEn.setVisibility(View.VISIBLE);
                    btn_upEn.setText("Skip");
                    btn_upEn.setTextSize(20);
                    btn_nextAr.setEnabled(true);
                    btn_nextAr.setText(">");
                    btn_nextAr.setTextSize(20);

                //form 2nd page to last page
                } else if (position > 0 && position < adapter.ICONZ.length-1) {

                    btn_upEn.setEnabled(true);
                    btn_upEn.setVisibility(View.VISIBLE);
                    btn_nextAr.setEnabled(true);
                    btn_nextAr.setText(">");
                    btn_nextAr.setTextSize(20);
                    btn_upEn.setText("Skip");
                    btn_upEn.setTextSize(20);

                //last page
                } else if (position == adapter.ICONZ.length-1){
                    btn_upEn.setEnabled(true);
                    btn_upEn.setVisibility(View.VISIBLE);
                    btn_upEn.setVisibility(View.GONE);
                    btn_nextAr.setEnabled(true);
                    btn_nextAr.setText("Done");
                    btn_upEn.setText("");
                    btn_nextAr.setTextSize(15);
                    btn_upEn.setTextSize(15);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //silder Imgs
        ImgArray = new ArrayList<>();
        ImgArray.add(R.drawable.s01);
        ImgArray.add(R.drawable.s02);
        ImgArray.add(R.drawable.s03);

        //

        //lib slider in the main activity (method)
        loadIMG(ImgArray);
    }
    //timer for viewpager page scroll
    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), seconds * 1000, seconds * 1000); // delay
        // in
        // milliseconds
    }
    // this is an inner class... for the timer to work in background
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page >= adapter.ICONZ.length-1) { // In my case the number of pages are 5
                        timer.cancel();
                        enterEnglish();
                    }
                    else {
                        mViewPager.setCurrentItem(page+1);
                    }
                }
            });

        }
    }
    //to set app orientation to english always
    public void localization(){
        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    //MAIN slider related
    public void loadIMG(ArrayList<Integer> arryListy){
        for (int i = 0; i < arryListy.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(arryListy.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit.CenterCrop)
                    .setOnSliderClickListener(MainActivity.this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putInt("currentImg", i);

            mDemoSlider.addSlider(textSliderView);
        }
        // you can change animasi, time page and anythink.. read more on github
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator_duck));
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(MainActivity.this);

    }
    //MAIN slider related
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    //MAIN slider related
    @Override
    public void onSliderClick(BaseSliderView slider) {


    }
    //MAIN slider related
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //MAIN slider related
    @Override
    public void onPageSelected(int position) {


        Log.d("Slider Demo", "Page Changed: " + position);
    }
    //MAIN slider related
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //find View By ID Method
    private void Initializing(){

        Gallery = findViewById(R.id.btnGallery);
        projects = findViewById(R.id.btnProjects);
        Services = findViewById(R.id.btnServices);
        Product = findViewById(R.id.btnProduct);
        Clients = findViewById(R.id.btnClients);
        AboutUs = findViewById(R.id.btnAboutUs);
        ContactUS = findViewById(R.id.btnContacUs);
        Subsidiaries = findViewById(R.id.btnSubsidiaries);
        mDemoSlider = (SliderLayout) findViewById(R.id.MainSilder);
        mViewPager = findViewById(R.id.MainViewPager);
        adapter = new MainSplashAdapter(MainActivity.this);
        btn_upEn = findViewById(R.id.Main_BTN_up_English);
        btn_nextAr = findViewById(R.id.Main_BTN_next_Arabic);
        //i used these to hide the splash screen and show the Main activity
        splashlayout = findViewById(R.id.Main_splash_Relative);
        MainLayout = findViewById(R.id.MainLayout);
        LanguageBtn = findViewById(R.id.btnArabic_English);


    }
    //Set action to MainActivity Buttons
    private void btns(){
        ContactUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ContactUsActivity.class);
                startActivity(i);
            }
        });

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(i);
            }
        });

        Clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ClientsActivity.class);
                startActivity(i);
            }
        });

        Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProductActivity.class);
                startActivity(i);
            }
        });
        Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(i);
            }
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProjectsActivity.class);
                startActivity(i);
            }
        });

        /*Subsidiaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Subsidiaries.class);
                startActivity(i);
            }
        });*/

        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(i);
            }
        });

        LanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(i);*/
                if(language==2){
                    enterArabic();
                    LanguageBtn.setText("English");
                }else {
                    enterEnglish();
                    LanguageBtn.setText("عربي");
                }


            }
        });


        btn_nextAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage == adapter.ICONZ.length-1){
                    //arabic button
                    enterArabic();

                }else {
                    //back button
                    mViewPager.setCurrentItem(mCurrentPage+1);
                }

            }
        });

        btn_upEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage == adapter.ICONZ.length-1){
                    enterEnglish();

                }else if (mCurrentPage==0){
                    //Skip button
                    enterEnglish();
                }
                else {
                    //Upbutton
                    enterEnglish();

/*
                    mViewPager.setCurrentItem(mCurrentPage - 1);
*/
                }
            }
        });



    }
    //start app as english Method
    private void enterEnglish(){
        language=2;
        splashlayout.setVisibility(View.GONE);
        MainLayout.setVisibility(View.VISIBLE);
        SharedPreferences.Editor editor = getSharedPreferences("LangKey", MODE_PRIVATE).edit();
        editor.putInt("languageNum", language);
        editor.apply();

        projects.setText("Projects");
        Services.setText("Services");
        Product.setText("Product");
        Clients.setText("Clients");
        Gallery.setText("Gallery");
        AboutUs.setText("About Us");
/*
        Subsidiaries.setText("Subsidiaries");
*/
        ContactUS.setText("Contact Us");
    }
    //start app as Arabic Method
    private void enterArabic(){
        language=1;
        splashlayout.setVisibility(View.GONE);
        MainLayout.setVisibility(View.VISIBLE);
        SharedPreferences.Editor editor = getSharedPreferences("LangKey", MODE_PRIVATE).edit();
        editor.putInt("languageNum", language);
        editor.apply();

        projects.setText("المشروعات");
        Services.setText("الخدمات");
        Product.setText("المنتجات");
        Clients.setText("العملاء");
        Gallery.setText("المعرض");
        AboutUs.setText("عن الشركة");
/*
        Subsidiaries.setText("الشركات الفرعية");
*/
        ContactUS.setText("تواصل معنا");
    }

}
