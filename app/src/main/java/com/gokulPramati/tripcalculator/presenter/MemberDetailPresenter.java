package com.gokulPramati.tripcalculator.presenter;

import android.content.Context;

import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.MemberDetailListener;
import com.gokulPramati.tripcalculator.listener.MemberDetailsFieldValidationListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.viewcontract.MemberDetailContract;
import com.gokulPramati.tripcalculator.viewinteractor.MemberDetailDataValidationInteractor;
import com.gokulPramati.tripcalculator.viewinteractor.MemberDetailInteractor;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberDetailPresenter implements MemberDetailListener, MemberDetailsFieldValidationListener {

    MemberDetailContract memberDetailContract;
    private MemberDetailInteractor memberDetailInteractor;
    private MemberDetailDataValidationInteractor memberDetailDataValidationInteractor;
    public MemberDetailPresenter(MemberDetailContract memberDetailContract){
        this.memberDetailContract=memberDetailContract;
    }

    @Override
    public void onExpenditureAdded(MemberExpenditures memberExpenditures) {
        memberDetailContract.addExpenditure(memberExpenditures);
    }

    @Override
    public void onExpenditureUpdated(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void onExpenditureDeleted(MemberExpenditures memberExpenditures) {

    }

    @Override
    public void onExpenditureDescError() {
        memberDetailContract.setExpenditureDescError();
    }

    @Override
    public void onExpenditureAmonutError() {
        memberDetailContract.setExpenditureAmountError();
    }

    @Override
    public void onValidationSuccess(MemberExpenditures expenditures) {
        memberDetailContract.onValidationSuccess(expenditures);

    }

    /**
     * validate Expenditure Data
     * @param memberExpenditures
     */
    public void validateExpenditureData(MemberExpenditures memberExpenditures){
        memberDetailDataValidationInteractor= new MemberDetailDataValidationInteractor(this);
        memberDetailDataValidationInteractor.validateExpenditureData(memberExpenditures);
    }

    /**
     * add Expenditure
     * @param memberExpenditures
     * @param context
     */
    public void addExpenditure(MemberExpenditures memberExpenditures, Context context){
        memberDetailInteractor= new MemberDetailInteractor(
                this, DatabaseHelper.getInstance(context));
        memberDetailInteractor.addExpenditure(memberExpenditures);
    }
}
