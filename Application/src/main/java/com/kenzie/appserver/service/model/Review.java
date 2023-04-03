package com.kenzie.appserver.service.model;

public class Review {
    private Customer name;
    private String reviewByCustomer;

    public Review(Customer name, String reviewByCustomer){
        this.name = name;
        this.reviewByCustomer = reviewByCustomer;
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
