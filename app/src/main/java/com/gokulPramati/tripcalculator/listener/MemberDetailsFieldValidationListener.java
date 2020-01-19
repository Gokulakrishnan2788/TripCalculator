package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.MemberExpenditures;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailsFieldValidationListener {
     void onExpenditureDescError();
     void onExpenditureAmonutError();
     void onValidationSuccess(MemberExpenditures expenditures);
}
