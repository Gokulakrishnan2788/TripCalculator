package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberReport {
    private long tripId;
    private long memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private String initialContribution;
    private String totalExpenditure;
    private String refundOrBalancePay;
    private String commonExp;

    public MemberReport(long tripId, long memberId, String name, String email, String phoneNumber,
                        String initialContribution,
                        String totalExpenditure,String commonExp, String refundOrBalancePay) {
        this.tripId = tripId;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.initialContribution = initialContribution;
        this.totalExpenditure = totalExpenditure;
        this.refundOrBalancePay = refundOrBalancePay;
        this.commonExp=commonExp;
    }
    public MemberReport(){

    }
    //Setters
    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setInitialContribution(String initialContribution) {
        this.initialContribution = initialContribution;
    }

    public void setTotalExpenditure(String totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public void setRefundOrBalancePay(String refundOrBalancePay) {
        this.refundOrBalancePay = refundOrBalancePay;
    }

    //getters


    public long getTripId() {
        return tripId;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getInitialContribution() {
        return initialContribution;
    }

    public String getTotalExpenditure() {
        return totalExpenditure;
    }

    public String getRefundOrBalancePay() {
        return refundOrBalancePay;
    }

    public String getCommonExp() {
        return commonExp;
    }

    public void setCommonExp(String commonExp) {
        this.commonExp = commonExp;
    }
}
