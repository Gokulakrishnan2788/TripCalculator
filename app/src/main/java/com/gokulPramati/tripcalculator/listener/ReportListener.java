package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface ReportListener {
     void onReportError(String error);
     void onReportReady(Trip trip, TripReport tripReport);

}
