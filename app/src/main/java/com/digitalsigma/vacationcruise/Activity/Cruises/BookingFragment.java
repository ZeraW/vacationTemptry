package com.digitalsigma.vacationcruise.Activity.Cruises;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.digitalsigma.vacationcruise.Activity.ServiceActivity;
import com.digitalsigma.vacationcruise.Models.Utils;
import com.digitalsigma.vacationcruise.R;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BookingFragment extends Fragment {
    View view;
    private TextView submitBtn;
    private EditText editName, editPhone, editEmail, editNaionality, editPersonsNum, editStartDate, editRooms, editEndDate, editSpecialReq;
    private String id,stName, stPhone, stEmail, stNationality, stPersonsNum, stStartDate = "", stRooms, stEndDate = "", stSpecialReq = "";
    final Calendar myCalendar = Calendar.getInstance();
    private RequestQueue mRequestQueue;

    CountryCodePicker ccp,ccpPhone;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_booking, container, false);
        init(view);
        datePicker();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingValidations();
            }
        });

        if (getActivity()!=null){
            id = ((CruisesActivity)getActivity()).getId();
        }


        return view;
    }

    private void datePicker() {
        final DatePickerDialog.OnDateSetListener StartDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(1);
            }

        };

        final DatePickerDialog.OnDateSetListener EndDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(2);
            }

        };

        editStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (getActivity() != null) {
                    new DatePickerDialog(getActivity(), StartDate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            }
        });


        editEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (getActivity() != null) {
                    new DatePickerDialog(getActivity(), EndDate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            }
        });
    }

    private void updateLabel(int check) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("shit", "updateLabel: " + stStartDate);
        if (check == 1) {
            stStartDate = sdf.format(myCalendar.getTime());
            editStartDate.setText(stStartDate);
        } else if (check == 2) {
            stEndDate = sdf.format(myCalendar.getTime());
            editEndDate.setText(stEndDate);
        }

    }

    private void init(View v) {
        submitBtn = v.findViewById(R.id.booking_submit);
        editSpecialReq = v.findViewById(R.id.Form_specialRequest);
        editName = v.findViewById(R.id.Form_name);
        editEmail = v.findViewById(R.id.Form_email);
        editPhone = v.findViewById(R.id.Form_phone);
        editPersonsNum = v.findViewById(R.id.Form_persons);
        editStartDate = v.findViewById(R.id.Form_Start_date);
        editEndDate = v.findViewById(R.id.Form_End_date);
        editNaionality = v.findViewById(R.id.Form_nationality);
        editRooms = v.findViewById(R.id.FOrm_rooms);


        //try
        ccp = (CountryCodePicker) v.findViewById(R.id.ccp);
        ccpPhone = (CountryCodePicker) v.findViewById(R.id.ccpNum);
        ccpPhone.registerCarrierNumberEditText(editPhone);



    }

    private void bookingValidations() {
        stSpecialReq = editSpecialReq.getText().toString();
        stName = editName.getText().toString();
        //stPhone = editPhone.getText().toString();
        stPhone = ccpPhone.getFormattedFullNumber();
        String PhoneCheck = editPhone.getText().toString();

        stEmail = editEmail.getText().toString();
        //stNationality = editNaionality.getText().toString();
        stNationality =ccp.getSelectedCountryName();

        stRooms = editRooms.getText().toString();
        stPersonsNum = editPersonsNum.getText().toString();


        //check email validations
        String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(regEx);
        Matcher valitMail = p.matcher(stEmail);


        if (stName.isEmpty()) {
            editName.setError("Required Field");
            editName.requestFocus();
        } else if (PhoneCheck.isEmpty()) {
            editPhone.setError("Required Field");
            editPhone.requestFocus();
        } else if (stEmail.isEmpty()) {
            editEmail.setError("Required Field");
            editEmail.requestFocus();
        }else if (!valitMail.find()) {
            editEmail.setError("Email Incorrect");
            editEmail.requestFocus();
        }  else if (stNationality.isEmpty()) {
            editNaionality.setError("Required Field");
            editNaionality.requestFocus();
        } else if (stPersonsNum.isEmpty()) {
            editPersonsNum.setError("Required Field");
            editPersonsNum.requestFocus();
        } else if (stRooms.isEmpty()) {
            editRooms.setError("Required Field");
            editRooms.requestFocus();
        } else if (stStartDate.isEmpty()) {
            editStartDate.setError("Required Field");
            editStartDate.requestFocus();
        } else if (stEndDate.isEmpty()) {
            editEndDate.setError("Required Field");
            editEndDate.requestFocus();
        } else {
            Toast.makeText(getActivity(), "do do", Toast.LENGTH_SHORT).show();
            sendRequest(stName,stPhone,stEmail,stNationality,stPersonsNum,stRooms,stStartDate,stEndDate,stSpecialReq);

            Log.e("phone", "init: phone : "+stPhone +" country : " + stNationality);
        }
    }

    private void sendRequest(String name, String phone, String email, String nationality, String persons, String rooms, String startDate, String endDate, String note) {
        String url = Utils.BOOKING + id +"/booking";
        JSONObject details= new JSONObject();
        try {
            details.put("name",name);
            details.put("phone",phone);
            details.put("email",email);
            details.put("nationality",nationality);
            details.put("persons",persons);
            details.put("rooms",rooms);
            details.put("start_date",startDate);
            details.put("end_date",endDate);
            details.put("note",note);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("sendRequest", "OBJECT: " + details );
        Log.e("sendRequest", "url: " + url );


        JsonObjectRequest PostRequest = new JsonObjectRequest(Request.Method.POST, url, details, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("sendRequest", "onResponse: " + response );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("sendRequest", "onErrorResponse: "+error);
                /*String body;
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                Log.e("sendRequest", "statusCode  " + statusCode);

                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                    Log.e("sendRequest", "body " + body);
                } catch (UnsupportedEncodingException e) {
                    // exception
                }*/



            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        if (getActivity()!=null)
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(PostRequest);


    }
}
