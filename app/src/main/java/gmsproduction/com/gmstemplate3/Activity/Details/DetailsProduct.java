package gmsproduction.com.gmstemplate3.Activity.Details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gmsproduction.com.gmstemplate3.Activity.BaseActivity;
import gmsproduction.com.gmstemplate3.R;

public class DetailsProduct extends BaseActivity {

    ImageView detailsImg;
    TextView  detailsdesc ;

    String title,img,desc,toolbarTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_product);

        Initialize();
        getExtra();
        setTitle(title);


        detailsdesc.setText(desc);
        Picasso.with(DetailsProduct.this).load(img).fit().centerInside().into(detailsImg);

    }

    private void Initialize(){
        //description
        detailsdesc = findViewById(R.id.DetailsDesc_Product);
        //Img
        detailsImg = findViewById(R.id.DetailsIMG_Product);

    }
    private void getExtra(){
        Intent extra = getIntent();
        title = extra.getStringExtra("title");
        img = extra.getStringExtra("images");
        desc = extra.getStringExtra("text");
        toolbarTitle = extra.getStringExtra("ToolbarTitle");

    }
}
