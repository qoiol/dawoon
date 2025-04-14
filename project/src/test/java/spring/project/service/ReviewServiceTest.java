package spring.project.service;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.project.domain.Review;
import spring.project.domain.User;
import spring.project.domain.Workout;
import spring.project.repository.MemoryReviewRepository;

public class ReviewServiceTest {

    ReviewService reviewService;
    MemoryReviewRepository reviewRepository;

//    @BeforeEach
//    public void beforeEach(){
//
//        reviewRepository = new MemoryReviewRepository();
//        reviewService = new ReviewService(reviewRepository);
//    }
//    @AfterEach
//    public void afterEach(){
//        reviewRepository.clearStore();
//    }

    @Test
    void createTest(){
        Review review = new Review();
        review.setTitle("테스트리뷰");
        review.setUser(User.builder().id("test").build());
        review.setContent("asdfas;dlkfj");
        review.setWorkout(Workout.builder().workoutId(0).build());
        review.setScore(5);
        long id = reviewService.createReview(review);

        Assertions.assertThat(id).isEqualTo(review.getId());
    }
}
