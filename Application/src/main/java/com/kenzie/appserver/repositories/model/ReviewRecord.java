package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Customer;

@DynamoDBTable(tableName = "ReviewDatabase")
public class ReviewRecord {
    private String name;
    private String reviewByCustomer;

    public ReviewRecord(String name, String reviewByCustomer) {
        this.name = Customer.class.getName();
        this.reviewByCustomer = reviewByCustomer;
    }

    public ReviewRecord() {

    }

    @DynamoDBHashKey(attributeName = "customerName") public String getName() {
        return Customer.class.getName();
    }

    public void setName(String name) {
        this.name = Customer.class.getName();
    }

    @DynamoDBAttribute(attributeName = "customerReview") public String getReviewByCustomer() {
        return reviewByCustomer;
    }

    public void setReviewByCustomer(String reviewByCustomer) {
        this.reviewByCustomer = reviewByCustomer;
    }
}
