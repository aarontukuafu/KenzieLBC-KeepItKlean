package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Customer;

import javax.validation.constraints.NotEmpty;

public class ReviewResponse {
    @NotEmpty
    @JsonProperty("name")
    private Customer name;
    @NotEmpty
    @JsonProperty("reviewByCustomer")
    private String reviewByCustomer;

    public ReviewResponse(Customer name, String reviewByCustomer) {
        this.name = name;
        this.reviewByCustomer = reviewByCustomer;
    }

    public ReviewResponse() {

    }

    public Customer getName() {
        return name;
    }

    public void setName(Customer name) {
        this.name = name;
    }

    public String getReviewByCustomer() {
        return reviewByCustomer;
    }

    public void setReviewByCustomer(String reviewByCustomer) {
        this.reviewByCustomer = reviewByCustomer;
    }
}
