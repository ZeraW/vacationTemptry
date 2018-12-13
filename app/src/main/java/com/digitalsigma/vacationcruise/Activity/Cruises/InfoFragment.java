package com.digitalsigma.vacationcruise.Activity.Cruises;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalsigma.vacationcruise.R;


public class InfoFragment extends Fragment {
    private View view;
    private String name, description, itinerary, departure;
    private TextView textName, textDescription, textItinerary, textDeparture;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profle, container, false);
        init(view);
        textName.setText(name);

        Log.e("desc", "onCreateView: " + description );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textDescription.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
            textItinerary.setText(Html.fromHtml(itinerary, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textDescription.setText(Html.fromHtml(description));
            textItinerary.setText(Html.fromHtml(itinerary));
        }
        textDeparture.setText(departure);


        return view;
    }

    private void init(View v) {
        if (getActivity() != null) {
            //lang = ((CruisesActivity) getActivity()).getIdLANG();
            name = ((CruisesActivity) getActivity()).getName();
            description = ((CruisesActivity) getActivity()).getDescription();
            itinerary = ((CruisesActivity) getActivity()).getItinerary();
            departure = ((CruisesActivity) getActivity()).getDeparture();
        }

        textName = v.findViewById(R.id.Info_name);
        textDescription = v.findViewById(R.id.Info_desc);
        textItinerary = v.findViewById(R.id.Info_itinerary);
        textDeparture = v.findViewById(R.id.Info_destination);

    }


}
