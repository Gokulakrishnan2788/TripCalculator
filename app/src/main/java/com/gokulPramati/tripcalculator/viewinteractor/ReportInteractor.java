package com.gokulPramati.tripcalculator.viewinteractor;

import android.content.Context;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.ReportListener;
import com.gokulPramati.tripcalculator.model.MemberReport;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class ReportInteractor {
    private ReportListener reportListener;
    private Trip trip;
    private TripReport tripReport;
    DatabaseHelper databaseHelper;
    public ReportInteractor(ReportListener reportListener){
        this.reportListener=reportListener;
    }
    
    public void generateReport(Context context, int trip_id){
        if(trip_id>0){
            databaseHelper=DatabaseHelper.getInstance(context);
            trip=databaseHelper.getTrip(trip_id);
            List<MemberReport> memberReports= new ArrayList<>();
            for(int i=0;i<2;i++){
                if(i==0){
                    MemberReport memberReport= new MemberReport(1, 1, "Raja",
                            "raja@gmail.com", "9943251796", "5000",
                            "8500","3500", "0");
                    memberReports.add(memberReport);
                }else if(i==1){
                    MemberReport memberReport= new MemberReport(1, 2, "Rghu",
                            "raja@gmail.com", "9943251796", "8000",
                            "6500","3500", "1000");
                    memberReports.add(memberReport);
                }else{
                    MemberReport memberReport= new MemberReport(1, 2, "balu",
                            "raja@gmail.com", "9943251796", "8000",
                            "6500","3500", "1000");
                    memberReports.add(memberReport);
                }
            }
            tripReport  = new TripReport(memberReports);
            reportListener.onReportReady(trip,tripReport);
        }else {
            reportListener.onReportError(context.getString(R.string.invalid_trip_id));
        }
        

    }
}
