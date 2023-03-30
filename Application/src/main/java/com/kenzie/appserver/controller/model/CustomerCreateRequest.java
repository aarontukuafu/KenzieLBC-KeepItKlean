package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.DaysOfWeek;

import javax.validation.constraints.NotEmpty;

public class CustomerCreateRequest {

    @NotEmpty
    @JsonProperty("userId")
    private String userId;

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @NotEmpty
    @JsonProperty("daysOfWeek")
    private String daysOfWeek;

    @NotEmpty
    @JsonProperty("secondDayOfWeek")
    private String secondDayOfWeek;

    @NotEmpty
    @JsonProperty("pickupTime")
    private String pickupTime;

    @NotEmpty
    @JsonProperty("numOfBins")
    private int numOfBins;

    @NotEmpty
    @JsonProperty("isCancelled")
    private boolean isCancelled;

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

    public String getSecondDayOfWeek() {
        return secondDayOfWeek;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public void setSecondDayOfWeek(String secondDayOfWeek) {
        this.secondDayOfWeek = secondDayOfWeek;


    }
}
