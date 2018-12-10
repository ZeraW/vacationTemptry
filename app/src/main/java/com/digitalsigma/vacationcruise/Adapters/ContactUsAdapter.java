package com.digitalsigma.vacationcruise.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.digitalsigma.vacationcruise.Activity.ContactUsUtilities;
import com.digitalsigma.vacationcruise.Models.ContactUsModel;
import com.digitalsigma.vacationcruise.R;

/**
 * Created by Hima on 4/18/2018.
 */

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ContactUsModel> mArrayList;
    ContactUsUtilities utilities;

    public ContactUsAdapter(Context mContext, ArrayList<ContactUsModel> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        utilities= new ContactUsUtilities(mContext);
    }

    @Override
    public ContactUsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_contactus, parent, false);
        return new ContactUsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactUsAdapter.ViewHolder holder, final int position) {
        ContactUsModel currentItem = mArrayList.get(position);
        final String text = currentItem.getData();
        final int images = currentItem.getImg();

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        utilities.openMapLocation();
                        break;
                    case 1:
                        utilities.phoneCall(utilities.phoneNumber);
                        break;
                    case 2:
                        mContext.startActivity(utilities.getOpenFacebookIntent(mContext,utilities.facebookPageID));
                        break;
                    case 3:
                        utilities.openEmail(utilities.emailStr);
                        break;
                    case 4:
                        utilities.openWebsite(utilities.websiteStr);
                        break;
                    /*case 5:
                        utilities.openYoutube(utilities.youtubeChannel);
                        break;*/

                }

            }
        });

        holder.data_name.setText(text);
        Picasso.with(mContext).load(images).into(holder.imgs);



    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imgs;
        TextView data_name;
        LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);
            imgs = itemView.findViewById(R.id.ContactUs_IMG);
            data_name = itemView.findViewById(R.id.ContactUS_TXT);
            linear = itemView.findViewById(R.id.ContactUs_linear);
        }
    }
}