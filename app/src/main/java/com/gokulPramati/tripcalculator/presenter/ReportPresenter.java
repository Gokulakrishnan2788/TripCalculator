package com.gokulPramati.tripcalculator.presenter;

import android.content.Context;

import com.gokulPramati.tripcalculator.listener.ReportListener;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;
import com.gokulPramati.tripcalculator.viewcontract.ReportContract;
import com.gokulPramati.tripcalculator.viewinteractor.ReportInteractor;


/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class ReportPresenter implements ReportListener {
    ReportContract reportContract;
    ReportInteractor reportInteractor;

    public ReportPresenter( ReportContract reportContract) {
        this.reportContract = reportContract;
    }

    public void generateReport(Context context,int trip_id) {
        reportInteractor= new ReportInteractor(this);
        reportInteractor.generateReport(context, trip_id);
    }

    @Override
    public void onReportError(String error) {
    reportContract.onReportError(error);
    }

    @Override
    public void onReportReady(Trip trip, TripReport tripReport) {
    reportContract.onReportReady(trip,tripReport);
    }
}
