package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.xspec.L;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.model.ReviewRecord;
import com.kenzie.appserver.repositories.model.ReviewRecordRepository;
import com.kenzie.appserver.service.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRecordRepository reviewRecordRepository;

    private CacheStore cacheStore;

    private DynamoDBMapper mapper;

    @Autowired
    public ReviewService(ReviewRecordRepository reviewRecordRepository) {
        this.reviewRecordRepository = reviewRecordRepository;
    }

    public Review postNewReview(Review review) {
        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setName(review.getName());
        reviewRecord.setReviewByCustomer(review.getReviewByCustomer());
        reviewRecordRepository.save(reviewRecord);
        return review;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();

        Iterable<ReviewRecord> iterator = reviewRecordRepository.findAll();
        for (ReviewRecord record : iterator) {
            reviews.add(new Review(record.getName(), record.getReviewByCustomer()));
        }
        return reviews;
    }
}
