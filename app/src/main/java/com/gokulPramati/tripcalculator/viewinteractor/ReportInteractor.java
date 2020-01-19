package com.gokulPramati.tripcalculator.viewinteractor;

import android.content.Context;

import com.gokulPramati.tripcalculator.R;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.ReportListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.model.MemberReport;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;
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
    List<MemberReport> memberReports = new ArrayList<>();
    List<TripMember> tripMembers = new ArrayList<>();

    public ReportInteractor(ReportListener reportListener) {
        this.reportListener = reportListener;
    }

    public void generateReport(Context context, int trip_id) {
        if (trip_id >= 0) {
            databaseHelper = DatabaseHelper.getInstance(context);
            trip = databaseHelper.getTrip(trip_id);
            tripMembers = databaseHelper.getAllTripMember(trip_id);
            float commonShare = Float.parseFloat(trip.getCommonExpenditureAmount()) / tripMembers.size();
            for (int i = 0; i < tripMembers.size(); i++) {
                MemberReport memberReport = new MemberReport();
                memberReport.setName(tripMembers.get(i).getName());
                memberReport.setTripId(tripMembers.get(i).getTripId());
                memberReport.setMemberId(tripMembers.get(i).getId());
                memberReport.setEmail(tripMembers.get(i).getEmail());
                memberReport.setPhoneNumber(tripMembers.get(i).getPhoneNumber());
                memberReport.setInitialContribution(tripMembers.get(i).getInitialContribution());
                memberReport.setCommonExp(String.valueOf(commonShare));
                float otherExp = getTotalExp(trip_id, tripMembers.get(i).getId());
                float totalExp = commonShare + otherExp;
                memberReport.setTotalExpenditure(String.valueOf(totalExp));
                float balancepay = Float.parseFloat(tripMembers.get(i).getInitialContribution()) - (totalExp);
                memberReport.setRefundOrBalancePay(String.valueOf(balancepay));
                memberReports.add(memberReport);
            }
            tripReport = new TripReport(memberReports);
            reportListener.onReportReady(trip, tripReport);
        } else {
            reportListener.onReportError(context.getString(R.string.invalid_trip_id));
        }


    }


    public float getTotalExp(long trip_id, long member_id) {
        float totalExp = 0.0f;
        List<MemberExpenditures> expenditures = databaseHelper.getAllTripMemberExpenditure(trip_id, member_id);
        try {
            for (int i = 0; i < expenditures.size(); i++) {
                totalExp = totalExp + Float.parseFloat(expenditures.get(i).getAmount());
            }
        } catch (Exception e) {

        }
        return totalExp;
    }
}
