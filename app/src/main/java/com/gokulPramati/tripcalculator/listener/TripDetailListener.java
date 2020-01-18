package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailListener {
    public void onMemberAdded(TripMember tripMember);
    public void onMemberUpdated(TripMember tripMember);
    public void onMemberDeleted(TripMember tripMember);
}
