package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripListener {
     void onTripAdded(Trip trip);
     void onTripUpdated(Trip trip);
     void onTripDeleted(Trip trip);

}
