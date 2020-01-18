package com.gokulPramati.tripcalculator.viewinteractor;

import android.text.TextUtils;

import com.gokulPramati.tripcalculator.listener.TripDetailFieldValidationListener;
import com.gokulPramati.tripcalculator.listener.TripFieldValidationListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripDetailDataValidationInteractor {
    TripMember tripMember;
    // static String ALPHA_PATTERN ="^[a-zA-Z]+(\\s[a-zA-Z]+)?$";
    TripDetailFieldValidationListener tripDetailFieldValidationListener;

    public TripDetailDataValidationInteractor(TripDetailFieldValidationListener tripDetailFieldValidationListener){
        this.tripDetailFieldValidationListener=tripDetailFieldValidationListener;
    }
    /**
     * Validating form
     */
    public void validateTripMemberData(TripMember tripMember) {
        this.tripMember=tripMember;
        if (!validateName()) {
            if(tripDetailFieldValidationListener!=null){
                tripDetailFieldValidationListener.onMemberNameError();
            }
            return;
        }

        if (!validateEmail()) {
            if(tripDetailFieldValidationListener!=null){
                tripDetailFieldValidationListener.onMemberEmailError();
            }
            return;
        }

        if (!validatePhoneNumber()) {
            if(tripDetailFieldValidationListener!=null){
                tripDetailFieldValidationListener.onMemberPhoneError();
            }
            return;
        }
        if(!validateInitialPay()){
            if(tripDetailFieldValidationListener!=null){
                tripDetailFieldValidationListener.onMemberInitialPayError();
            }

            return;
        }
        //success
        if(tripDetailFieldValidationListener!=null){
            tripDetailFieldValidationListener.onValidationSuccess(tripMember);
        }

    }

    /**
     * Validate Name
     * @return
     */
    private  boolean validateName(){
        return tripMember != null && (tripMember.getName() != null && !tripMember.getName().isEmpty())&& tripMember.getName().length() > 3;

    }

    /**
     *
     * @return
     */
    private  boolean validateEmail() {
        return tripMember != null && !TextUtils.isEmpty(tripMember.getEmail()) && android.util.Patterns.EMAIL_ADDRESS.matcher(tripMember.getEmail()).matches();
    }

    /**
     * Validate phone
     * @return
     */
    private  boolean validatePhoneNumber(){
        return tripMember != null && (tripMember.getPhoneNumber() != null&&
                !tripMember.getPhoneNumber().isEmpty()) &&
                tripMember.getPhoneNumber().length() >9 &&
                tripMember.getPhoneNumber().length() <14;
    }

    /**
     * Validate initial pay
     * @return
     */
    private  boolean validateInitialPay(){
        return tripMember != null && (tripMember.getInitialContribution()!=null && !tripMember.getInitialContribution().isEmpty())&& Float.parseFloat(tripMember.getInitialContribution())>=0;
    }
}
