package com.kenzie.appserver.service.model;

public class Review {
    private String name;
    private String reviewByCustomer;

    public Review(String name, String reviewByCustomer){
        this.name = name;
        this.reviewByCustomer = reviewByCustomer;
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
