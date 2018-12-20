package com.digitalsigma.vacationcruise.View.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.Model.Cruises;
import com.digitalsigma.vacationcruise.R;
import com.digitalsigma.vacationcruise.View.Activity.Details.CruisesDetailsActivity;
import com.digitalsigma.vacationcruise.ViewModel.FragViewModel;


public class InfoFragment extends Fragment {
    private View view;
    private String name, description, itinerary, departure;
    private TextView textName, textDescription, textItinerary, textDeparture;
    private int id;
    private FragViewModel mViewModel;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profle, container, false);
        init(view);

        if (getActivity() != null) {
            mViewModel = ViewModelProviders.of(getActivity()).get(FragViewModel.class);

            mViewModel.getCruises(id).observe(getActivity(), new Observer<Cruises>() {
                @Override
                public void onChanged(@Nullable Cruises cruises) {
                    if (cruises != null) {
                        textName.setText(cruises.getTitle());

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            textDescription.setText(Html.fromHtml(cruises.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                            textItinerary.setText(Html.fromHtml(cruises.getItinerary(), Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            textDescription.setText(Html.fromHtml(cruises.getDescription()));
                            textItinerary.setText(Html.fromHtml(cruises.getItinerary()));
                        }
                        textDeparture.setText(cruises.getDestination());

                    }
                }
            });

        }


        return view;
    }

    private void init(View v) {
        if (getActivity() != null) {
            id = ((CruisesDetailsActivity) getActivity()).getId();
        }

        textName = v.findViewById(R.id.Info_name);
        textDescription = v.findViewById(R.id.Info_desc);
        textItinerary = v.findViewById(R.id.Info_itinerary);
        textDeparture = v.findViewById(R.id.Info_destination);

    }


}
