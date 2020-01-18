package com.gokulPramati.tripcalculator.model;

import java.util.List;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripReport {
    private List<MemberReport> memberReports;

    public TripReport(List<MemberReport> memberReports) {
        this.memberReports = memberReports;
    }

    public List<MemberReport> getMemberReports() {
        return memberReports;
    }

    public void setMemberReports(List<MemberReport> memberReports) {
        this.memberReports = memberReports;
    }
}
