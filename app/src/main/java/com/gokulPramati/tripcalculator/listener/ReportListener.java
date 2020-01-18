package com.gokulPramati.tripcalculator.listener;

import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;

import java.lang.reflect.Member;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface ReportListener {
    public void onReportError(String error);
    public void onReportReady(Trip trip, TripReport tripReport);

}
