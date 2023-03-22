package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

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

    public CustomerResponse(String userId, String daysOfWeek, String pickupTime, String numOfBins) {
        this.userId = userId;
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
