package com.gokulPramati.tripcalculator.viewcontract;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripContract {


     void addTrip(Trip trip );
     void updateTrip(Trip trip);
     void deleteTrip(Trip trip);
     void setTripNameError();
     void setLocationError();
     void setDescriptionError();
     void setCommonExpenditureError();
     void onValidationSuccess(Trip trip);

}
