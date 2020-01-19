package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailListener {
    public void onExpenditureAdded(MemberExpenditures memberExpenditures);
    public void onExpenditureUpdated(MemberExpenditures memberExpenditures);
    public void onExpenditureDeleted(MemberExpenditures memberExpenditures);
}
