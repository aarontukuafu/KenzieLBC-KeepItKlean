package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.DaysOfWeek;
import com.kenzie.appserver.service.model.PickupTime;

@DynamoDBTable(tableName = "CustomerDatabase") public class CustomerRecord {
    private String userId;
    private DaysOfWeek daysOfWeek;
    private PickupTime pickupTime;
    private int numOfBins;

    private  CustomerRecord(String userId, DaysOfWeek daysOfWeek, PickupTime pickupTime, int numOfBins) {
        this.userId = userId;
        this.daysOfWeek = daysOfWeek;
        this.pickupTime = pickupTime;
        this.numOfBins = numOfBins;
    }

    @DynamoDBHashKey(attributeName = "UserId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "DaysOfWeek")
    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @DynamoDBAttribute(attributeName = "PickupTime")
    public PickupTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(PickupTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    @DynamoDBAttribute(attributeName = "NumberOfBins")
    public int getNumOfBins() {
        return numOfBins;
    }

    public void setNumOfBins(int numOfBins) {
        this.numOfBins = numOfBins;
    }

}
