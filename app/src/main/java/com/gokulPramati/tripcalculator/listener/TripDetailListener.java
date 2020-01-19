package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailListener {
     void onMemberAdded(TripMember tripMember);
     void onMemberUpdated(TripMember tripMember);
     void onMemberDeleted(TripMember tripMember);
}
