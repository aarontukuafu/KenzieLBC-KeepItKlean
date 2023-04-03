package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.Customer;

import javax.validation.constraints.NotEmpty;

public class ReviewResponse {
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @NotEmpty
    @JsonProperty("reviewByCustomer")
    private String reviewByCustomer;

    public ReviewResponse(String name, String reviewByCustomer) {
        this.name = name;
        this.reviewByCustomer = reviewByCustomer;
    }

    public ReviewResponse() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReviewByCustomer() {
        return reviewByCustomer;
    }

    public void setReviewByCustomer(String reviewByCustomer) {
        this.reviewByCustomer = reviewByCustomer;
    }
}
