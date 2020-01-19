package com.gokulPramati.tripcalculator.listener;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailFieldValidationListener {
     void onMemberNameError();
     void onMemberEmailError();
     void onMemberPhoneError();
     void onMemberInitialPayError();
     void onValidationSuccess(TripMember tripMember);
}
