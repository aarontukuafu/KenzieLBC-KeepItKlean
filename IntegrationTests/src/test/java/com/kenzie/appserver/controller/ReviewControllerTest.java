package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.QueryUtility;
import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@IntegrationTest
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReviewService reviewService;

    private final MockNeat mockNeat = MockNeat.threadLocal();
    private QueryUtility queryUtility;

    @BeforeAll
    public void setup() {
        queryUtility = new QueryUtility(mvc);
    }

    @Test
    public void can_post_new_review_successful() throws Exception {
        //GIVEN
        ReviewCreateRequest createRequest = new ReviewCreateRequest();
        createRequest.setName(mockNeat.strings().get());
        createRequest.setReviewByCustomer(mockNeat.strings().get());

        //WHEN
//        ResponseEntity<ReviewResponse> response = restTemplate.postForEntity("/review", createRequest, ReviewResponse.class);

        //THEN
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/review/" + createRequest.getName());
//
//        ReviewResponse responseBody = response.getBody();
//        assertThat(responseBody.getName()).isEqualTo(createRequest.getName());
//        assertThat(responseBody.getReviewByCustomer()).isEqualTo(createRequest.getReviewByCustomer());
        queryUtility.customerControllerClient.postNewReview(createRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void can_get_all_reviews() throws Exception {
        //GIVEN
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String name = mockNeat.strings().get();
            String reviewByCustomer = mockNeat.strings().get();
            Review review = new Review(name, reviewByCustomer);
            reviewService.postNewReview(review);
            reviews.add(review);
        }

        //WHEN
//        ResponseEntity<List<ReviewResponse>> response = restTemplate.exchange(
//                "/review",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ReviewResponse>>(){});

        //THEN
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        List<ReviewResponse> responseBody = response.getBody();
//        assertThat(responseBody.size()).isEqualTo(3);
//
//        for (int i = 0; i < 3; i++) {
//            assertThat(responseBody.get(i).getName()).isEqualTo(reviews.get(i).getName());
//            assertThat(responseBody.get(i).getReviewByCustomer()).isEqualTo(reviews.get(i).getReviewByCustomer());
        queryUtility.customerControllerClient.getAllReviews((ReviewCreateRequest) reviews)
                .andExpect(status().isOk());
    }
}

