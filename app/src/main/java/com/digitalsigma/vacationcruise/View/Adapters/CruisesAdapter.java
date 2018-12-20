package com.digitalsigma.vacationcruise.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.Model.Cruises;
import com.digitalsigma.vacationcruise.Model.Gallery;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.Utils.Const;
import com.digitalsigma.vacationcruise.View.Activity.Details.CruisesDetailsActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hima on 4/16/2018.
 */

public class CruisesAdapter extends RecyclerView.Adapter<CruisesAdapter.ViewHolder> {
    private Context mContext;
    private List<Cruises> mArrayList;
    private int lang;

    public CruisesAdapter(Context mContext, List<Cruises> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    public CruisesAdapter() {
    }

    @Override
    public CruisesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_service, parent, false);
        return new CruisesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CruisesAdapter.ViewHolder holder, int position) {
        final Cruises currentItem = mArrayList.get(position);
        final int id = currentItem.getId();

        final String name = currentItem.getTitle();
        final String logo = Const.BASE_IMG_URL +currentItem.getHeaderImg();
        final String desc = currentItem.getDescription();
        final String itinerary = currentItem.getItinerary();
        final String departure =  currentItem.getDestination();
        final List<Gallery> imgArray = currentItem.getGallery();

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // to send data to new class using putExtra
                Intent intent=new Intent(mContext,CruisesDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("image",logo);
                intent.putExtra("desc",desc);
                intent.putExtra("itinerary",itinerary);
                intent.putExtra("departure",departure);
                //intent.putExtra("imgArray",imgArray);

                Bundle bundle = new Bundle();
                bundle.putSerializable("imgArray", (Serializable) imgArray);
                intent.putExtras(bundle);


               /* if(lang==1){
                    intent.putExtra("ToolbarTitle","الخدمات");
                }else{
                    intent.putExtra("ToolbarTitle","Services");
                }*/

                mContext.startActivity(intent);

            }
        });

        holder.data_name.setText(name);
        Picasso.with(mContext).load(logo).into(holder.imgs);


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
            imgs = itemView.findViewById(R.id.Service_IMG);
            data_name = itemView.findViewById(R.id.Service_TXT);
            linear = itemView.findViewById(R.id.Service_linear);
        }
    }
}