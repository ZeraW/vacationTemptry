package com.digitalsigma.vacationcruise.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.digitalsigma.vacationcruise.View.Activity.Details.DetailsGallery;
import com.digitalsigma.vacationcruise.Model.Gallery;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.Utils.Const;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Hima on 4/16/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context mContext;
    private List<Gallery> mArrayList;
    private int lang;

    public GalleryAdapter(Context mContext, List<Gallery> mArrayList , int lang) {
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

        final Gallery logo =  mArrayList.get(position);

        String imgs = Const.BASE_IMG_URL + logo.getPath();


        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to send data to new class using putExtra
                Intent intent=new Intent(mContext,DetailsGallery.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ArraylistOFimg", (Serializable) mArrayList);
                intent.putExtras(bundle);


                //intent.putStringArrayListExtra("ArraylistOFimg",mArrayList);
                intent.putExtra("imgnum",position);

                if(lang==1){
                    intent.putExtra("ToolbarTitle","المعرض");
                }else{
                    intent.putExtra("ToolbarTitle","Gallery");
                }

                mContext.startActivity(intent);

            }
        });

        Picasso.with(mContext).load(imgs).fit().centerCrop().into(holder.imgs);


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
