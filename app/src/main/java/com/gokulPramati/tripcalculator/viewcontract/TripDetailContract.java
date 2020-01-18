package com.gokulPramati.tripcalculator.viewcontract;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailContract {
    public void addMember(TripMember tripMember );
    public void updateMember(TripMember tripMember);
    public void deleteMember(TripMember tripMember);
    public void setMemberNameError();
    public void setMemberEmailError();
    public void setMemberPhoneError();
    public void setMemberInitialContributionError();
    public void onValidationSuccess(TripMember tripMember);
}
