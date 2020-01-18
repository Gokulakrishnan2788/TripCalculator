package com.gokulPramati.tripcalculator.viewinteractor;

import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripDetailListener;
import com.gokulPramati.tripcalculator.listener.TripListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripDetailDataInteractor {
    TripDetailListener tripDetailListener;
    DatabaseHelper databaseHelper;
    public TripDetailDataInteractor(TripDetailListener tripDetailListener, DatabaseHelper databaseHelper) {
        this.tripDetailListener = tripDetailListener;
        this.databaseHelper = databaseHelper;
    }

    public void addMember(TripMember tripMember) {
        long insertedId = -1;
        insertedId = databaseHelper.addMember(tripMember);
        if (insertedId > 0) {
            tripMember.setId(insertedId);
            if (tripDetailListener != null) {
                tripDetailListener.onMemberAdded(tripMember);
            }
        }

    }

    public void updateMember(TripMember tripMember) {
        int affectedRows = -1;
        affectedRows = databaseHelper.updateMember(tripMember);
        if (affectedRows > 0) {
            if (tripDetailListener != null) {
                tripDetailListener.onMemberUpdated(tripMember);
            }
        }

    }

    public void deleteMember(TripMember tripMember) {
        databaseHelper.deleteMember(tripMember.getTripId(),tripMember.getId());
        if (tripDetailListener != null) {
            tripDetailListener.onMemberDeleted(tripMember);
        }
    }
}
