package com.gokulPramati.tripcalculator.presenter;

import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripFieldValidationListener;
import com.gokulPramati.tripcalculator.listener.TripListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.viewcontract.TripContract;
import com.gokulPramati.tripcalculator.viewinteractor.TripDataInteractor;
import com.gokulPramati.tripcalculator.viewinteractor.TripDataValidtionIntractor;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TripPresenter implements TripListener, TripFieldValidationListener {

    TripDataInteractor tripDataInteractor;
    TripDataValidtionIntractor tripDataValidtionIntractor;
    TripContract tripContract;
    DatabaseHelper databaseHelper;

    public TripPresenter(TripContract tripContract, DatabaseHelper databaseHelper) {
        this.tripContract = tripContract;
        this.databaseHelper=databaseHelper;
    }

    @Override
    public void onTripNameError() {
        tripContract.setTripNameError();

    }

    @Override
    public void onTripLocationError() {
        tripContract.setLocationError();

    }

    @Override
    public void onTripDescriptionError() {

        tripContract.setDescriptionError();
    }

    @Override
    public void onCommonExpenditureError() {

        tripContract.setCommonExpenditureError();
    }

    @Override
    public void onValidationSuccess(Trip trip) {
    tripContract.onValidationSuccess(trip);
    }

    @Override
    public void onTripAdded(Trip trip) {
        tripContract.addTrip(trip);

    }

    @Override
    public void onTripUpdated(Trip trip) {

    }

    @Override
    public void onTripDeleted(Trip trip) {

    }

    public void validateTripData(Trip trip){
        tripDataValidtionIntractor= new TripDataValidtionIntractor(this);
        tripDataValidtionIntractor.validateTripData(trip);
    }

    public void addTripData(Trip trip){
        tripDataInteractor= new TripDataInteractor(this,databaseHelper);
        tripDataInteractor.addTrip(trip);
    }

}
