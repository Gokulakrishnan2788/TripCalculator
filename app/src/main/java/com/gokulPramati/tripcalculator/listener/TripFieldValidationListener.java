package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripFieldValidationListener {
     void onTripNameError();
     void onTripLocationError();
     void onTripDescriptionError();
     void onCommonExpenditureError();
     void onValidationSuccess(Trip trip);
}
