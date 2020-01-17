package com.gokulPramati.tripcalculator.view.fragment;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.viewcontract.TripContract;

public class TripFragment extends Fragment implements TripContract {

    DatabaseHelper databaseHelper;
    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void addTrip(Trip trip) {

    }

    @Override
    public void updateTrip(Trip trip) {

    }

    @Override
    public void deleteTrip(Trip trip) {

    }

    @Override
    public void setTripNameError() {

    }

    @Override
    public void setLocationError() {

    }

    @Override
    public void setDescriptionError() {

    }

    @Override
    public void setCommonExpenditureError() {

    }

    public void validateTripDetails(Trip trip)
    {

    }
}
