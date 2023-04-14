import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.model.ReviewRecord;
import com.kenzie.appserver.repositories.model.ReviewRecordRepository;
import com.kenzie.appserver.service.ReviewService;
import com.kenzie.appserver.service.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewServiceTest {
    private ReviewRecordRepository reviewRecordRepository;
    private CacheStore cacheStore;
    private DynamoDBMapper mapper;
    private ReviewService reviewService;

    @BeforeEach
    void setup() {
        reviewRecordRepository = mock(ReviewRecordRepository.class);
        reviewService = new ReviewService(reviewRecordRepository);
    }

    @Test
    public void testPostNewReview() {
        // GIVEN
        Review review = new Review("John Doe", "Great service!");

        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setName("John Doe");
        reviewRecord.setReviewByCustomer("Great service!");

        when(reviewRecordRepository.save(reviewRecord)).thenReturn(reviewRecord);

        // WHEN
        Review result = reviewService.postNewReview(review);

        // THEN
        assertNotNull(result, "The result is not null");
        assertEquals(review.getName(), result.getName(), "The name matches");
        assertEquals(review.getReviewByCustomer(), result.getReviewByCustomer(), "The review matches");
    }

    @Test
    public void testGetAllReviews() {
        // GIVEN
        List<ReviewRecord> reviewRecords = new ArrayList<>();
        reviewRecords.add(new ReviewRecord("John Doe", "Great service!"));
        reviewRecords.add(new ReviewRecord("Jane Doe", "Excellent experience!"));

        when(reviewRecordRepository.findAll()).thenReturn(reviewRecords);

        // WHEN
        List<Review> result = reviewService.getAllReviews();

        // THEN
        assertNotNull(result, "The result is not null");
        assertEquals(2, result.size(), "The size matches");

        Review review1 = result.get(0);
        assertEquals("John Doe", review1.getName(), "The name of review 1 matches");
        assertEquals("Great service!", review1.getReviewByCustomer(), "The review of review 1 matches");

        Review review2 = result.get(1);
        assertEquals("Jane Doe", review2.getName(), "The name of review 2 matches");
        assertEquals("Excellent experience!", review2.getReviewByCustomer(), "The review of review 2 matches");
    }
}

