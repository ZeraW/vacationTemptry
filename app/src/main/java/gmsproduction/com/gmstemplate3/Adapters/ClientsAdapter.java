package gmsproduction.com.gmstemplate3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gmsproduction.com.gmstemplate3.Activity.Details.DetailsProjects;
import gmsproduction.com.gmstemplate3.Models.MainModel;
import gmsproduction.com.gmstemplate3.R;

/**
 * Created by Hima on 4/16/2018.
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<MainModel> mArrayList;
    private ArrayList<String> mArrayList2;

    public ClientsAdapter(Context mContext, ArrayList<MainModel> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;

    }

    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_clients, parent, false);
        return new ClientsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClientsAdapter.ViewHolder holder, int position) {
        final MainModel currentItem = mArrayList.get(position);
        final String ar_name = currentItem.getTitle();
        final String logo = currentItem.getLogo();
        final String desc = currentItem.getText();


        holder.data_name.setText(ar_name);
        holder.data_Desc.setText(desc);
        Picasso.with(mContext).load(logo).fit().centerCrop().into(holder.imgs);


    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imgs;
        TextView data_name,data_Desc;

        public ViewHolder(View itemView) {
            super(itemView);
            imgs = itemView.findViewById(R.id.Clients_IMG);
            data_Desc = itemView.findViewById(R.id.Clients_Desc);
            data_name = itemView.findViewById(R.id.Clients_Title);
        }
    }
}
