package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class Trip {
    private String name;
    private String location;
    private String description;
    private long id;
    private String commonExpenditureAmount;

    /**
     * Trip MemberExpenditureBuilder
     * @param name
     * @param location
     * @param description
     */
    public Trip(String name, String location, String description) {
        this.name = name;
        this.description = description;
        this.location=location;
    }

    /**
     * Trip MemberExpenditureBuilder
     * @param builder
     */
    public Trip(TripBuilder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.description = builder.description;
        this.commonExpenditureAmount = builder.commonExpenditureAmount;
        this.location=builder.location;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public String getCommonExpenditureAmount() {
        return commonExpenditureAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCommonExpenditureAmount(String commonExpenditureAmount) {
        this.commonExpenditureAmount = commonExpenditureAmount;
    }

    /**
     * MemberExpenditureBuilder Class
     */
    public static class TripBuilder {
        private String name;
        private String description;
        private long id;
        private String commonExpenditureAmount;
        private String location;

        public TripBuilder name(String name) {
            this.name = name;
            return this;
        }
        public TripBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TripBuilder location(String location) {
            this.location = location;
            return this;
        }
        public TripBuilder id(int id) {
            this.id = id;
            return this;
        }

        public TripBuilder commonExpenditure(String commonExpenditure) {
            this.commonExpenditureAmount = commonExpenditure;
            return this;
        }

        /**
         * Trip builder method
         * @return Trip
         */
        public Trip build(){
            return new Trip(this);
        }
    }
}
