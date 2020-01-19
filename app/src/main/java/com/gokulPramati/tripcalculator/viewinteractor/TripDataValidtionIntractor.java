package com.gokulPramati.tripcalculator.viewinteractor;

import com.gokulPramati.tripcalculator.listener.TripFieldValidationListener;
import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TripDataValidtionIntractor {
    Trip trip;
   // static String ALPHA_PATTERN ="^[a-zA-Z]+(\\s[a-zA-Z]+)?$";
    TripFieldValidationListener tripFieldValidationListener;

    public TripDataValidtionIntractor(TripFieldValidationListener tripFieldValidationListener){
        this.tripFieldValidationListener=tripFieldValidationListener;
    }
    /**
     * Validating form
     */
  public void validateTripData(Trip trip) {
        this.trip=trip;
        if (!validateName()) {
            if(tripFieldValidationListener!=null){
                tripFieldValidationListener.onTripNameError();
            }
            return;
        }

        if (!validateLocation()) {
            if(tripFieldValidationListener!=null){
                tripFieldValidationListener.onTripLocationError();
            }
            return;
        }

        if (!validateDescription()) {
            if(tripFieldValidationListener!=null){
                tripFieldValidationListener.onTripDescriptionError();
            }
            return;
        }
        if(!validateCommonExpenditure()){
            if(tripFieldValidationListener!=null){
                tripFieldValidationListener.onCommonExpenditureError();
            }

            return;
        }
        //success
        if(tripFieldValidationListener!=null){
            tripFieldValidationListener.onValidationSuccess(trip);
        }

    }

    /**
     * Validate Name
     * @return
     */
    private  boolean validateName(){
        return trip != null && (trip.getName() != null && !trip.getName().isEmpty())&& trip.getName().length() > 2;
               // && trip.getName().matches(ALPHA_PATTERN);
    }

    /**
     *
     * @return
     */
    private  boolean validateLocation(){
        return trip != null && (trip.getLocation() != null && !trip.getLocation().isEmpty()) && trip.getLocation().length() >2 ;
    }

    /**
     * Validate Description
     * @return
     */
    private  boolean validateDescription(){
        return trip != null && (trip.getDescription() != null&& !trip.getDescription().isEmpty()) && trip.getDescription().length() > 2;
    }

    /**
     * Validate Expenditure
     * @return
     */
    private  boolean validateCommonExpenditure(){

        return trip != null && (trip.getCommonExpenditureAmount()!=null && !trip.getCommonExpenditureAmount().isEmpty())&& Float.parseFloat(trip.getCommonExpenditureAmount())>=0;
    }
}
