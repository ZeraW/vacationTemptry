package com.digitalsigma.vacationcruise.View.Adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalsigma.vacationcruise.Model.Cruises;
import com.digitalsigma.vacationcruise.Model.Gallery;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.Utils.Const;
import com.digitalsigma.vacationcruise.View.Activity.CruisesActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;


public class CruisesPageListAdapter extends PagedListAdapter<Cruises,CruisesPageListAdapter.ViewHolder> {
    private Context context;
    protected CruisesPageListAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_service, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cruises currentItem = getItem(position);
        if (currentItem!=null){

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
                    Intent intent=new Intent(context,CruisesActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    intent.putExtra("image",logo);
                    intent.putExtra("desc",desc);
                    intent.putExtra("itinerary",itinerary);
                    intent.putExtra("departure",departure);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("imgArray", (Serializable) imgArray);
                    intent.putExtras(bundle);

                    context.startActivity(intent);
                }
            });

            holder.data_name.setText(name);
            Picasso.with(context).load(logo).into(holder.imgs);

        }else {
            Toast.makeText(context, "Null", Toast.LENGTH_LONG).show();
        }
    }


    private static DiffUtil.ItemCallback<Cruises> diffCallback = new DiffUtil.ItemCallback<Cruises>() {
        @Override
        public boolean areItemsTheSame(Cruises oldItem, Cruises newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Cruises oldItem, Cruises newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
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
