package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class TripMember {
    private int tripId;
    private int id;
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
    public TripMember(Builder builder) {
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
    public int getTripId() {
        return tripId;
    }

    public int getId() {
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

    /**
     * Trip Member Builder
     */
    public static class Builder {
        private int tripId;
        private int id;
        private String name;
        private String email;
        private String phoneNumber;
        private String initialContribution;

        private Builder tripId(int tripId) {
            this.tripId = tripId;
            return this;
        }

        private Builder id(int id) {
            this.id = id;
            return this;
        }

        private Builder name(String name) {
            this.name = name;
            return this;
        }

        private Builder email(String email) {
            this.email = email;
            return this;
        }

        private Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        private Builder initialContribution(String initialContribution) {
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
