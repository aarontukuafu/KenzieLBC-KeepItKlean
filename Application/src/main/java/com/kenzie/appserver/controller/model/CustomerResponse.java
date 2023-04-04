package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Customer;

import javax.validation.constraints.NotEmpty;

@JsonInclude//(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    @NotEmpty
    @JsonProperty("userId")
    private String userId;

    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @JsonProperty("daysOfWeek")
    private String daysOfWeek;

    //@NotEmpty
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

    public CustomerResponse(String userId, String daysOfWeek, String pickupTime, int numOfBins) {
        this.userId = userId;
        this.daysOfWeek = daysOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    public CustomerResponse(){}

    public CustomerResponse(String userId, String name, String daysOfWeek, String secondDayOfWeek, String pickupTime, int numOfBins, boolean isCancelled) {
        this.userId = userId;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.secondDayOfWeek = secondDayOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
        this.isCancelled = isCancelled;
    }

    public CustomerResponse(String userId, String name, String daysOfWeek, String secondDayOfWeek, String pickupTime, int numOfBins) {
        this.userId = userId;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.secondDayOfWeek = secondDayOfWeek;
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

    public int getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(int numOfBins) {
        this.numOfBins = numOfBins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondDayOfWeek() {
        return secondDayOfWeek;
    }

    public void setSecondDayOfWeek(String secondDayOfWeek) {
        this.secondDayOfWeek = secondDayOfWeek;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;


    }
}
