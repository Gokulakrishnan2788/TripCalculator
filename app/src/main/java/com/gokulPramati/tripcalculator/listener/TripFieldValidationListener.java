package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripFieldValidationListener {
    public void onTripNameError();
    public void onTripLocationError();
    public void onTripDescriptionError();
    public void onCommonExpenditureError();
    public void onValidationSuccess(Trip trip);
}
