package com.gokulPramati.tripcalculator.listener;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailFieldValidationListener {
    public void onMemberNameError();
    public void onMemberEmailError();
    public void onMemberPhoneError();
    public void onMemberInitialPayError();
    public void onValidationSuccess(TripMember tripMember);
}
