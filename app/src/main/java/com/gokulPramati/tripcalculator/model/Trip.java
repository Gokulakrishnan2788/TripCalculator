package com.gokulPramati.tripcalculator.model;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class Trip {
    private String name;
    private String location;
    private String description;
    private int id;
    private String commonExpenditureAmount;

    /**
     * Trip Builder
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
     * Trip Builder
     * @param builder
     */
    public Trip(Builder builder) {
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

    public int getId() {
        return id;
    }

    public String getCommonExpenditureAmount() {
        return commonExpenditureAmount;
    }

    /**
     * Builder Class
     */
    public static class Builder {
        private String name;
        private String description;
        private int id;
        private String commonExpenditureAmount;
        private String location;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder commonExpenditure(String commonExpenditure) {
            this.commonExpenditureAmount = commonExpenditureAmount;
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
