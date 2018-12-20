package com.digitalsigma.vacationcruise.View.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.digitalsigma.vacationcruise.Utils.EndlessRecyclerViewScrollListener;
import com.digitalsigma.vacationcruise.Model.Cruises;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.View.Adapters.CruisesAdapter;
import com.digitalsigma.vacationcruise.ViewModel.CruisesViewModel;

import java.util.ArrayList;
import java.util.List;

public class CruisesActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CruisesAdapter mAdapter;
    int language = 1;
    private EndlessRecyclerViewScrollListener scrollListener;
    private CruisesViewModel mViewModel;
    private List<Cruises>mList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_service);

        language = getIdLANG();
        //recycler View horizon orientation
        mRecyclerView = findViewById(R.id.Recycler_Service);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CruisesActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mList =  new ArrayList<>();
        mAdapter = new CruisesAdapter(CruisesActivity.this,mList);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel = ViewModelProviders.of(CruisesActivity.this).get(CruisesViewModel.class);
        mViewModel.getAllUsers(1).observe(this, new Observer<List<Cruises>>() {
            @Override
            public void onChanged(@Nullable List<Cruises> cruise) {
                //update RecyclerView

                if (cruise != null) {
                    mList.clear();
                    mList.addAll(cruise);
                }
                mAdapter.notifyDataSetChanged();


            }
        });


        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                mViewModel.getAllUsers(page+1);
            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);


    }



}
