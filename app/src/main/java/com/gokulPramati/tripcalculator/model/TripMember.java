package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripMember {
    private long tripId;
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String initialContribution;

    public TripMember(int tripId, String name, String email, String phone, String initialContribution) {
        this.tripId = tripId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
        this.initialContribution = initialContribution;
    }

    /**
     * @param builder
     */
    public TripMember(TripMemberBuilder builder) {
        this.tripId = builder.tripId;
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.initialContribution = builder.initialContribution;

    }

    /**
     * @return
     */
    public long getTripId() {
        return tripId;
    }

    public long getId() {
        return id;
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


    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setId(long id) {
        this.id = id;
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

    /**
     * Trip Member MemberExpenditureBuilder
     */
    public static class TripMemberBuilder {
        private long tripId;
        private long id;
        private String name;
        private String email;
        private String phoneNumber;
        private String initialContribution;

        private TripMemberBuilder tripId(int tripId) {
            this.tripId = tripId;
            return this;
        }

        private TripMemberBuilder id(int id) {
            this.id = id;
            return this;
        }

        private TripMemberBuilder name(String name) {
            this.name = name;
            return this;
        }

        private TripMemberBuilder email(String email) {
            this.email = email;
            return this;
        }

        private TripMemberBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        private TripMemberBuilder initialContribution(String initialContribution) {
            this.initialContribution = initialContribution;
            return this;
        }

        /**
         * @return TripMember
         */
        public TripMember build() {
            return new TripMember(this);
        }
    }


}
