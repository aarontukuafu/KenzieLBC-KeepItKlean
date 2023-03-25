package com.kenzie.appserver.service.model;

import java.sql.Time;

public class Customer {

    private String userId;
    private String name;
    private DaysOfWeek daysOfWeek;
    private PickupTime pickupTime;
    private int numOfBins;

    public Customer(String userId, DaysOfWeek daysOfWeek, PickupTime pickupTime, int numOfBins) {
        this.userId = userId;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public PickupTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(PickupTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(int numOfBins) {
        this.numOfBins = numOfBins;
    }

}
