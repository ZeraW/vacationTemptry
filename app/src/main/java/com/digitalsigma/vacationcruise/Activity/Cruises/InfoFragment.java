package com.digitalsigma.vacationcruise.Activity.Cruises;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitalsigma.vacationcruise.R;


public class InfoFragment extends Fragment {
    private View view;
    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profle, container, false);










        return view;
    }



}
