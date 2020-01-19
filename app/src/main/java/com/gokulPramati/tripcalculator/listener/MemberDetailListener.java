package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.MemberExpenditures;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface MemberDetailListener {
     void onExpenditureAdded(MemberExpenditures memberExpenditures);
     void onExpenditureUpdated(MemberExpenditures memberExpenditures);
     void onExpenditureDeleted(MemberExpenditures memberExpenditures);
}
