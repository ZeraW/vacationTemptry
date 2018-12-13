package com.digitalsigma.vacationcruise.Activity.Cruises;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalsigma.vacationcruise.Activity.GalleryActivity;
import com.digitalsigma.vacationcruise.Adapters.GalleryAdapter;
import com.digitalsigma.vacationcruise.R;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private int lang;
    private ArrayList<String> mGalleryList;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_galary, container, false);

        if (getActivity() != null) {
            //lang = ((CruisesActivity) getActivity()).getIdLANG();
            mGalleryList = ((CruisesActivity) getActivity()).getmImgList();
        }

        mRecyclerView = view.findViewById(R.id.Recycler_Gallery_Frag);
        LinearLayoutManager LayoutManagaer = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(LayoutManagaer);


        mAdapter = new GalleryAdapter(getContext(), mGalleryList, lang);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }


}
