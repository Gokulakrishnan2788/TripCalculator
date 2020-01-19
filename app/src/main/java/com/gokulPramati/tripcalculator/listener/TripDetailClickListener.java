package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailClickListener {
     void onAddMemberClick(int trip_id);
     void onMemberAdded();
     void onMemberItemClick(TripMember tripMember);
}
