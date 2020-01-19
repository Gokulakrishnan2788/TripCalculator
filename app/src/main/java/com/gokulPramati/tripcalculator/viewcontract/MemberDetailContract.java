package com.gokulPramati.tripcalculator.viewcontract;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;


/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailContract {
     void addExpenditure(MemberExpenditures memberExpenditures );
     void updateExpenditure(MemberExpenditures memberExpenditures);
     void deleteExpenditure(MemberExpenditures memberExpenditures);
     void setExpenditureDescError();
     void setExpenditureAmountError();
     void onValidationSuccess(MemberExpenditures memberExpenditures);
}
