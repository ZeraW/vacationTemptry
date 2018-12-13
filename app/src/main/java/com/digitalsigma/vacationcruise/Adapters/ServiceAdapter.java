package com.digitalsigma.vacationcruise.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.Activity.Cruises.CruisesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.digitalsigma.vacationcruise.Activity.Details.DetailsProjects;
import com.digitalsigma.vacationcruise.Models.MainModel;
import com.digitalsigma.vacationcruise.R;

/**
 * Created by Hima on 4/16/2018.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<MainModel> mArrayList;
    private int lang;

    public ServiceAdapter(Context mContext, ArrayList<MainModel> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_service, parent, false);
        return new ServiceAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        final MainModel currentItem = mArrayList.get(position);
        final String id = currentItem.getId();
        final String name = currentItem.getName();
        final String logo = currentItem.getLogo();
        final String desc = currentItem.getDescription();
        final String itinerary = currentItem.getItinerary();
        final String departure =  currentItem.getDeparture();
        final ArrayList<String> imgArray = currentItem.getImgArray();

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to send data to new class using putExtra
                Intent intent=new Intent(mContext,CruisesActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("image",logo);
                intent.putExtra("desc",desc);
                intent.putExtra("itinerary",itinerary);
                intent.putExtra("departure",departure);
                intent.putExtra("imgArray",imgArray);


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