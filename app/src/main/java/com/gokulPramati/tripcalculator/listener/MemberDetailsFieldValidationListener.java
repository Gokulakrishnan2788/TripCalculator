package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailsFieldValidationListener {
    public void onExpenditureDescError();
    public void onExpenditureAmonutError();
    public void onValidationSuccess(MemberExpenditures expenditures);
}
