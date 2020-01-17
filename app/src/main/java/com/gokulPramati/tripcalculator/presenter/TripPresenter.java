package com.gokulPramati.tripcalculator.presenter;

import com.gokulPramati.tripcalculator.listener.TripFieldValidationListener;
import com.gokulPramati.tripcalculator.listener.TripListener;
import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TripPresenter implements TripListener, TripFieldValidationListener {



    @Override
    public void onTripNameError() {

    }

    @Override
    public void onTripLocationError() {

    }

    @Override
    public void onTripDescriptionError() {

    }

    @Override
    public void onCommonExpenditureError() {

    }

    @Override
    public void onValidationSuccess() {

    }

    @Override
    public void onTripAdded(Trip trip) {

    }

    @Override
    public void onTripUpdated(Trip trip) {

    }

    @Override
    public void onTripDeleted(Trip trip) {

    }
}
