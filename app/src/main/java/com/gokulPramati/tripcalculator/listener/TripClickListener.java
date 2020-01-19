package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public interface TripClickListener {
    void onAddTripClick();
     void onTripAdded();
    void onTriItemClick(Trip trip);
}
