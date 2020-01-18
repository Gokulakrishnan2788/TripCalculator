package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface TripDetailClickListener {
    public void onAddMemberClick(int trip_id);
    public void onMemberAdded();
    public void onMemberItemClick(TripMember tripMember);
}
