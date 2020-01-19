package com.gokulPramati.tripcalculator.presenter;

import android.content.Context;

import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripDetailFieldValidationListener;
import com.gokulPramati.tripcalculator.listener.TripDetailListener;
import com.gokulPramati.tripcalculator.model.TripMember;
import com.gokulPramati.tripcalculator.viewcontract.TripDetailContract;
import com.gokulPramati.tripcalculator.viewinteractor.TripDetailDataInteractor;
import com.gokulPramati.tripcalculator.viewinteractor.TripDetailDataValidationInteractor;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripDetailPresenter implements TripDetailListener, TripDetailFieldValidationListener {
    TripDetailContract tripDetailContract;
   private TripDetailDataInteractor tripDetailDataInteractor;
   private TripDetailDataValidationInteractor tripDataValidtionIntractor;
    public TripDetailPresenter(TripDetailContract tripDetailContract){
        this.tripDetailContract=tripDetailContract;
    }

    @Override
    public void onMemberAdded(TripMember tripMember) {
        tripDetailContract.addMember(tripMember);
    }

    @Override
    public void onMemberUpdated(TripMember tripMember) {

    }

    @Override
    public void onMemberDeleted(TripMember tripMember) {

    }

    @Override
    public void onMemberNameError() {
        tripDetailContract.setMemberNameError();
    }

    @Override
    public void onMemberEmailError() {
        tripDetailContract.setMemberEmailError();
    }

    @Override
    public void onMemberPhoneError() {
        tripDetailContract.setMemberPhoneError();
    }

    @Override
    public void onMemberInitialPayError() {
        tripDetailContract.setMemberInitialContributionError();
    }

    @Override
    public void onValidationSuccess(TripMember tripMember) {
        tripDetailContract.onValidationSuccess(tripMember);
    }

    /**
     *
     * @param tripMember
     */
    public void validateMemberData(TripMember tripMember){
        tripDataValidtionIntractor= new TripDetailDataValidationInteractor(this);
        tripDataValidtionIntractor.validateTripMemberData(tripMember);
    }

    /**
     *
     * @param tripMember
     * @param context
     */
    public void addMemberData(TripMember tripMember, Context context){
        tripDetailDataInteractor= new TripDetailDataInteractor(
                this,DatabaseHelper.getInstance(context));
        tripDetailDataInteractor.addMember(tripMember);
    }

    /**
     *
     * @param commonExp
     * @param tripId
     * @param context
     */
    public void updateCommonExpense(String commonExp,int tripId,Context context){
        tripDetailDataInteractor= new TripDetailDataInteractor(
                this,DatabaseHelper.getInstance(context));
        tripDetailDataInteractor.updateCommonExpense(commonExp,tripId);
    }
}
