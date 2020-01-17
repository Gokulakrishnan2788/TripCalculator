package com.gokulPramati.tripcalculator.viewinteractor;

import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.TripListener;
import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TripDataInteractor {
    TripListener tripListener;
    DatabaseHelper databaseHelper;

    TripDataInteractor(TripListener tripListener, DatabaseHelper databaseHelper) {
        this.tripListener = tripListener;
        this.databaseHelper = databaseHelper;
    }

    public void addTrip(Trip trip) {
        long insertedId = -1;
        insertedId = databaseHelper.addTrip(trip);
        if (insertedId > 0) {
            trip.setId(insertedId);
            if (tripListener != null) {
                tripListener.onTripAdded(trip);
            }
        }

    }

    public void updateTrip(Trip trip) {
        int affectedRows = -1;
        affectedRows = databaseHelper.updateTrip(trip);
        if (affectedRows > 0) {
            if (tripListener != null) {
                tripListener.onTripUpdated(trip);
            }
        }

    }

    public void deleteTrip(Trip trip) {
        databaseHelper.deleteTrip(trip.getId());
        if (tripListener != null) {
            tripListener.onTripDeleted(trip);
        }
    }
}
