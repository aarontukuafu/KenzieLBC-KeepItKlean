package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.DaysOfWeek;

import javax.validation.constraints.NotEmpty;

public class CustomerCreateRequest {

    @NotEmpty
    @JsonProperty("userId")
    private String userId;

    @NotEmpty
    @JsonProperty("daysOfWeek")
    private String daysOfWeek;

    @NotEmpty
    @JsonProperty("pickupTime")
    private String pickupTime;

    @NotEmpty
    @JsonProperty("numOfBins")
    private String numOfBins;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(String numOfBins) {
        this.numOfBins = numOfBins;
    }
}