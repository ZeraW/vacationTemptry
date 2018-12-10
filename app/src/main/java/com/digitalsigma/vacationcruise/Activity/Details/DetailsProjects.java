package com.digitalsigma.vacationcruise.Activity.Details;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import com.digitalsigma.vacationcruise.R;

public class DetailsProjects extends AppCompatActivity {
    ImageView detailsImg,backbtn;
    TextView detailstitle , detailsdesc , toolBarTitle;

    String title,img,desc,toolbarTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localization();
        setContentView(R.layout.details_projects);

        Initialize();
        getExtra();


        detailstitle.setText(title);
        detailsdesc.setText(desc);
        toolBarTitle.setText(toolbarTitle);
        Picasso.with(DetailsProjects.this).load(img).fit().centerInside().into(detailsImg);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
    public void localization(){
        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
    private void Initialize(){
        //title
        detailstitle = findViewById(R.id.DetailsTitle);
        //description
        detailsdesc = findViewById(R.id.DetailsDesc);
        //Img
        detailsImg = findViewById(R.id.DetailsIMG);
        //back button (cuz i removed action)
        backbtn = findViewById(R.id.DetailsBackBtn);
        // tool bar title (cuz i removed action)
        toolBarTitle = findViewById(R.id.DetailsToolBarTitle);

    }
    private void getExtra(){
        Intent extra = getIntent();
        title = extra.getStringExtra("title");
        img = extra.getStringExtra("images");
        desc = extra.getStringExtra("text");
        toolbarTitle = extra.getStringExtra("ToolbarTitle");

    }



}
