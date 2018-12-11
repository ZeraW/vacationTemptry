package com.digitalsigma.vacationcruise.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.digitalsigma.vacationcruise.Activity.Details.DetailsGallery;
import com.digitalsigma.vacationcruise.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Hima on 4/16/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mArrayList;
    private int lang;

    public GalleryAdapter(Context mContext, ArrayList<String> mArrayList , int lang) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        this.lang = lang;

    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_gallery, parent, false);
        return new GalleryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, final int position) {

        final String logo =  mArrayList.get(position);


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to send data to new class using putExtra
                Intent intent=new Intent(mContext,DetailsGallery.class);
                intent.putStringArrayListExtra("ArraylistOFimg",mArrayList);
                intent.putExtra("imgnum",position);

                if(lang==1){
                    intent.putExtra("ToolbarTitle","المعرض");
                }else{
                    intent.putExtra("ToolbarTitle","Gallery");
                }

                mContext.startActivity(intent);

            }
        });

        Picasso.with(mContext).load(logo).fit().centerCrop().into(holder.imgs);


    }

    @Override
    public int getItemCount() {
        if (mArrayList.size()>0){
            return mArrayList.size();
        }else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imgs;
        LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);
            imgs = itemView.findViewById(R.id.GalleryImg);
            linear = itemView.findViewById(R.id.GalleryLinear);
        }
    }
}
