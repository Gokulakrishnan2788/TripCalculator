package com.gokulPramati.tripcalculator.viewcontract;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;


/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailContract {
    public void addExpenditure(MemberExpenditures memberExpenditures );
    public void updateExpenditure(MemberExpenditures memberExpenditures);
    public void deleteExpenditure(MemberExpenditures memberExpenditures);
    public void setExpenditureDescError();
    public void setExpenditureAmountError();
    public void onValidationSuccess(MemberExpenditures memberExpenditures);
}
