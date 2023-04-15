package com.kenzie.appserver.service.model;

import java.sql.Time;
import java.util.UUID;

public class Customer {

    private String userId;
    private String name;
    //can be change back to enum
    private String daysOfWeek;
    private String secondDayOfWeek;

    private String pickupTime;
    private int numOfBins;

//    private boolean isCancelled;

//    public Customer(String userId, String name, String daysOfWeek, String pickupTime, int numOfBins) {
//        this.userId = userId;
//        this.name = name;
//        this.daysOfWeek = daysOfWeek;
//        this.pickupTime = pickupTime;
//        this.numOfBins = numOfBins;
//    }

    public Customer(String userId, String name, String daysOfWeek,String secondDayOfWeek, String pickupTime, int numOfBins) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.secondDayOfWeek = secondDayOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    public Customer() {

    }


//    public Customer(String userId, String name, String daysOfWeek, String pickupTime, String secondDayOfWeek, int numOfBins, boolean isCancelled) {
//        this.userId = userId;
//        this.name = name;
//        this.daysOfWeek = daysOfWeek;
//        this.secondDayOfWeek = secondDayOfWeek;
//        this.pickupTime = pickupTime;
//        this.numOfBins = numOfBins;
//        this.isCancelled = false;
//    }

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

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(int numOfBins) {
        this.numOfBins = numOfBins;
    }

//    public boolean isCancelled(){ return isCancelled;}
//
//    public void setCancelled(boolean cancelled){ isCancelled = cancelled;}
    public String getSecondDayOfWeek() {
        return secondDayOfWeek;
    }

    public void setSecondDayOfWeek(String secondDayOfWeek) {
        this.secondDayOfWeek = secondDayOfWeek;
    }

}
