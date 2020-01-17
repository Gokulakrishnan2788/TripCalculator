package com.gokulPramati.tripcalculator.viewinteractor;

import com.gokulPramati.tripcalculator.listener.TripFieldValidationListener;
import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TripDataValidtionIntractor {
    Trip trip;
    static String ALPHA_PATTERN ="[A-Z][a-z]*";
    TripFieldValidationListener tripFieldValidationListener;

    TripDataValidtionIntractor(TripFieldValidationListener tripFieldValidationListener){
        this.tripFieldValidationListener=tripFieldValidationListener;
    }
    /**
     * Validating form
     */
    private void validateTripData(Trip trip) {
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
            tripFieldValidationListener.onValidationSuccess();
        }

    }

    /**
     * Validate Name
     * @return
     */
    private  boolean validateName(){
        return trip != null && trip.getName() != null && trip.getName().length() > 3 && trip.getName().matches(ALPHA_PATTERN);
    }

    /**
     *
     * @return
     */
    private  boolean validateLocation(){
        return trip != null && trip.getLocation() != null && trip.getLocation().length() > 3 ;
    }

    /**
     * Validate Description
     * @return
     */
    private  boolean validateDescription(){
        return trip != null && trip.getDescription() != null && trip.getName().length() > 5;
    }

    /**
     * Validate Expenditure
     * @return
     */
    private  boolean validateCommonExpenditure(){
        float amount = Float.parseFloat(trip.getCommonExpenditureAmount());
        return trip != null && trip.getCommonExpenditureAmount() != null && amount>0;
    }
}
