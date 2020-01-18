package com.gokulPramati.tripcalculator.viewcontract;

import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.MemberReport;
import com.gokulPramati.tripcalculator.model.TripReport;

import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public interface ReportContract {
    public void onReportError(String error);
    public void onReportReady(Trip trip, TripReport reports);
}
