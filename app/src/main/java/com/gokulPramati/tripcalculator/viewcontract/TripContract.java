package com.gokulPramati.tripcalculator.viewcontract;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripContract {


    public void addTrip(Trip trip );
    public void updateTrip(Trip trip);
    public void deleteTrip(Trip trip);
    public void setTripNameError();
    public void setLocationError();
    public void setDescriptionError();
    public void setCommonExpenditureError();

}
