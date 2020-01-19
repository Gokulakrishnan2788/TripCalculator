package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberExpenditures {

    private long tripId;
    private long memberId;
    private long id;
    private String amount;
    private String description;

    public MemberExpenditures(long tripId, long memberId, String amount, String description) {
        this.tripId = tripId;
        this.memberId = memberId;
        this.amount = amount;
        this.description = description;
    }

    public MemberExpenditures(MemberExpenditureBuilder builder) {
        this.id=builder.id;
        this.tripId = builder.tripId;
        this.memberId = builder.memberId;
        this.amount = builder.amount;
        this.description = builder.description;
    }

    public long getTripId() {
        return tripId;
    }

    public long getMemberId() {
        return memberId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Builder Class
     */
    public static class MemberExpenditureBuilder {
        private long id;
        private long tripId;
        private long memberId;
        private String amount;
        private String description;

        public MemberExpenditureBuilder id(long id) {
            this.id = id;
            return this;
        }
        public MemberExpenditureBuilder tripId(long tripId) {
            this.tripId = tripId;
            return this;
        }

        public MemberExpenditureBuilder memberId(long memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberExpenditureBuilder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public MemberExpenditureBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MemberExpenditures build() {
            return new MemberExpenditures(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
