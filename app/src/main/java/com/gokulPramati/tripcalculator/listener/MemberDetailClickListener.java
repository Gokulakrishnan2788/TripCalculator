package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailClickListener {
    public void onAddExpenditureClick(int member_id,int trip_id);
    public void onExpenditureAdded();

}
