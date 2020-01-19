package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripListener {
    public void onTripAdded(Trip trip);
    public void onTripUpdated(Trip trip);
    public void onTripDeleted(Trip trip);

}
