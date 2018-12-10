package com.digitalsigma.vacationcruise.Activity.Details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.digitalsigma.vacationcruise.Activity.BaseActivity;
import com.digitalsigma.vacationcruise.Activity.ContactUsUtilities;
import com.digitalsigma.vacationcruise.R;

public class DetailsProduct extends BaseActivity {

    ImageView detailsImg;
    TextView  detailsdesc ;
    ImageButton redirectToGapps;

    String title,img,desc,toolbarTitle,url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_product);

        Initialize();
        getExtra();
        setTitle(title);


        detailsdesc.setText(desc);
        Picasso.with(DetailsProduct.this).load(img).fit().centerInside().into(detailsImg);

        redirectToGapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new ContactUsUtilities(DetailsProduct.this).getOpenGoogleIntent(DetailsProduct.this,url));
            }
        });

    }

    private void Initialize(){
        //description
        detailsdesc = findViewById(R.id.DetailsDesc_Product);
        //Img
        detailsImg = findViewById(R.id.DetailsIMG_Product);
        // google play
        redirectToGapps = findViewById(R.id.googleplayURL);

    }
    private void getExtra(){
        Intent extra = getIntent();
        title = extra.getStringExtra("title");
        img = extra.getStringExtra("images");
        desc = extra.getStringExtra("text");
        toolbarTitle = extra.getStringExtra("ToolbarTitle");
        url = extra.getStringExtra("link");
    }
}
