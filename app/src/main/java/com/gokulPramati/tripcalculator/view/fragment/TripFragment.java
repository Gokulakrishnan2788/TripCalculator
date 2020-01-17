package com.gokulPramati.tripcalculator.view.fragment;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripClickListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.presenter.TripPresenter;
import com.gokulPramati.tripcalculator.viewcontract.TripContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TripFragment extends Fragment implements TripContract {

    DatabaseHelper databaseHelper;
    View view;
    TripPresenter tripPresenter;
    TripClickListener addTripClickListener;
    public TripFragment() {
        // Required empty public constructor
    }

    public void setAddTripListener(TripClickListener addTripListener){
       this.addTripClickListener=addTripListener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view== null)
        view =inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        return  view;
    }

   public void initView(){
        tripPresenter = new TripPresenter(this);
       FloatingActionButton fab = view.findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addTripClickListener.onAddTripClick();
           }
       });
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
        showToast("Invalid name");
    }

    @Override
    public void setLocationError() {
        showToast("Invalid location");
    }

    @Override
    public void setDescriptionError() {
        showToast("Invalid Desc");
    }

    @Override
    public void setCommonExpenditureError() {
        showToast("Invalid Expenditure");
    }

    @Override
    public void onValidationSuccess() {
        showToast("Validation Success");
        addTripClickListener.onTripAdded();
    }

    public void validateTripDetails(Trip trip)
    {
        tripPresenter.validateTripData(trip);
    }

    public void navigateToTripDetails(Trip trip){

    }

    public void updateTripList(){

    }

    private  void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
}
