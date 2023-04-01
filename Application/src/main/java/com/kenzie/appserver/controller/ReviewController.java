package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> postNewReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        Review review = new Review(reviewCreateRequest.getName(), reviewCreateRequest.getReviewByCustomer());

        reviewService.postNewReview(review);

        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setName(review.getName());
        reviewResponse.setReviewByCustomer(review.getReviewByCustomer());

        return ResponseEntity.created(URI.create("/review/" + reviewResponse.getName())).body(reviewResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();

        if (reviews == null || reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ReviewResponse> responseList = new ArrayList<>();
        for (Review review : reviews) {
            responseList.add(this.createReviewResponse(review));
        }
        return ResponseEntity.ok(responseList);
    }

    private ReviewResponse createReviewResponse(Review review) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setName(review.getName());
        reviewResponse.setReviewByCustomer(review.getReviewByCustomer());
        return reviewResponse;
    }
}
