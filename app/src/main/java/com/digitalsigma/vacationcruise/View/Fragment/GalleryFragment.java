package com.digitalsigma.vacationcruise.View.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalsigma.vacationcruise.View.Adapters.GalleryAdapter;
import com.digitalsigma.vacationcruise.Model.Cruises;
import com.digitalsigma.vacationcruise.Model.Gallery;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.View.Activity.Details.CruisesDetailsActivity;
import com.digitalsigma.vacationcruise.ViewModel.FragViewModel;

import java.util.List;


public class GalleryFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private int lang;
    private List<Gallery> mGalleryList;
    private int id;

    private FragViewModel mViewModel;

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
            id = ((CruisesDetailsActivity) getActivity()).getId();
        }

        mRecyclerView = view.findViewById(R.id.Recycler_Gallery_Frag);
        LinearLayoutManager LayoutManagaer = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(LayoutManagaer);


        if (getActivity() != null) {
            mViewModel = ViewModelProviders.of(getActivity()).get(FragViewModel.class);
            mViewModel.getCruises(id).observe(getActivity(), new Observer<Cruises>() {
                @Override
                public void onChanged(@Nullable Cruises cruises) {
                    if (cruises != null) {
                        mGalleryList =cruises.getGallery();
                        mAdapter = new GalleryAdapter(getContext(), mGalleryList, lang);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }
            });
        }


        return view;
    }


}
