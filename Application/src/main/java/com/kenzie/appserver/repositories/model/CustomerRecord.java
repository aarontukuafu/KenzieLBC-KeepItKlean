package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.DaysOfWeek;
import com.kenzie.appserver.service.model.PickupTime;

import java.util.Objects;

@DynamoDBTable(tableName = "CustomerDatabase") public class CustomerRecord {
    private String userId;
    private String name;
    private String daysOfWeek;
    private String secondDayOfWeek = null;
    private String pickupTime;
    private int numOfBins;

    private boolean isCancelled;


    public CustomerRecord(String userId,String name,String daysOfWeek, String pickupTime, int numOfBins) {
        this.userId = userId;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    public CustomerRecord(String userId, String name, String daysOfWeek, String secondDayOfWeek, String pickupTime, int numOfBins) {
        this.userId = userId;
        this.name = name;
        this.daysOfWeek = daysOfWeek;
        this.secondDayOfWeek = null;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    public CustomerRecord() {

    }
    //created empty constructor

    @DynamoDBHashKey(attributeName = "UserId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "DaysOfWeek")
    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @DynamoDBAttribute(attributeName = "PickupTime")
    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    @DynamoDBAttribute(attributeName = "NumberOfBins")
    public int getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(int numOfBins) {
        this.numOfBins = numOfBins;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerRecord)) return false;
        CustomerRecord that = (CustomerRecord) o;
        return numOfBins == that.numOfBins && userId.equals(that.userId) && daysOfWeek.equals(that.daysOfWeek) && pickupTime.equals(that.pickupTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, daysOfWeek, pickupTime, numOfBins);
    }
}
