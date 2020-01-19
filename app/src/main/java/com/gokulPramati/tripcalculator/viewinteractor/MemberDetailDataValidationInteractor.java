package com.gokulPramati.tripcalculator.viewinteractor;
import com.gokulPramati.tripcalculator.listener.MemberDetailsFieldValidationListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;


/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberDetailDataValidationInteractor {
    MemberExpenditures memberExpenditures;
    MemberDetailsFieldValidationListener memberDetailsFieldValidationListener;

    public MemberDetailDataValidationInteractor(MemberDetailsFieldValidationListener memberDetailsFieldValidationListener){
        this.memberDetailsFieldValidationListener=memberDetailsFieldValidationListener;
    }
    /**
     * Validating form
     */
    public void validateExpenditureData(MemberExpenditures memberExpenditures) {
        this.memberExpenditures=memberExpenditures;


        if (!validateDescription()) {
            if(memberDetailsFieldValidationListener!=null){
                memberDetailsFieldValidationListener.onExpenditureDescError();
            }
            return;
        }
        if(!validateAmount()){
            if(memberDetailsFieldValidationListener!=null){
                memberDetailsFieldValidationListener.onExpenditureAmonutError();
            }

            return;
        }
        //success
        if(memberDetailsFieldValidationListener!=null){
            memberDetailsFieldValidationListener.onValidationSuccess(memberExpenditures);
        }

    }


    /**
     * Validate Description
     * @return
     */
    private  boolean validateDescription(){
        return memberExpenditures != null && (memberExpenditures.getDescription() != null&& !memberExpenditures.getDescription().isEmpty()) && memberExpenditures.getDescription().length() > 2;
    }

    /**
     * Validate Expenditure
     * @return
     */
    private  boolean validateAmount(){

        return memberExpenditures != null && (memberExpenditures.getAmount()!=null && !memberExpenditures.getAmount().isEmpty())&& Float.parseFloat(memberExpenditures.getAmount())>=0;
    }
}
