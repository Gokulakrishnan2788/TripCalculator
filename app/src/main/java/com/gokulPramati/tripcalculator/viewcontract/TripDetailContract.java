package com.gokulPramati.tripcalculator.viewcontract;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailContract {
     void addMember(TripMember tripMember );
     void updateMember(TripMember tripMember);
     void deleteMember(TripMember tripMember);
     void setMemberNameError();
     void setMemberEmailError();
     void setMemberPhoneError();
     void setMemberInitialContributionError();
     void onValidationSuccess(TripMember tripMember);
}
