package spring.project.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;
import spring.project.domain.User;
import spring.project.domain.Workout;
import spring.project.repository.ReviewRepository;
import spring.project.repository.UserRepository;
import spring.project.repository.WorkoutRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
@Transactional
public class ReviewServiceIntegrationTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    UserService userService;
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void create(){
        User user = new User();
        user.setUserType("trainer");
        user.setEmail("aaaa@bbb.ccc");
        user.setPassword("admin");
        user.setId("admin");
        user.setName("테스트");
        userService.join(user);
        System.out.println("user.getId() = " + user.getId());

        Workout workout = new Workout();
        workout.setWorkoutType("요가");
        workout.setWorkoutDifficulty("하");
        workout.setWorkoutName("asdf");
        workout.setTrainerId("admin");
        workout.setTrainerName("테스트");
        workoutService.addWorkout(workout);

        Review review = new Review();
        review.setContent("asdf");
        review.setUserId("admin");
        review.setWorkoutId(0);
        review.setTitle("sdfkfk");
        review.setScore(5);

        long id = reviewService.createReview(review);

        System.out.println("id = " + id);
        System.out.println("review.getLikeCount() = " + review.getLikeCount());
        System.out.println(" = " +review.getPostedDate());

        List<Review> reviews = reviewService.findReviews();
        System.out.println("reviews.size() = " + reviews.size());
    }
    
    @Test
    void findReviews(){

    }
}
