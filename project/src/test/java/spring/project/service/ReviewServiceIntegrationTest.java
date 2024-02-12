package spring.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.project.domain.Report;
import spring.project.domain.Review;
import spring.project.domain.User;
import spring.project.domain.Workout;

import java.util.List;

@SpringBootTest
public class ReviewServiceIntegrationTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

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
        workoutService.addWorkout(workout);

        Review review = new Review();

        review.setContent("asdf");
        review.setUserId("admin");
        review.setWorkoutId(1);
        review.setTitle("sdfkfk");
        review.setScore(5);

        long id = reviewService.createReview(review);

        System.out.println("id = " + id);
        System.out.println("review.getLikeCount() = " + review.getLikeCount());
        System.out.println(" = " +review.getPostedDate());

        List<Review> reviews = reviewService.findReviews();
        System.out.println("reviews.size() = " + reviews.size());

        System.out.println("reviews.get(0).getWorkoutName() = " + reviews.get(0).getWorkoutName());
        System.out.println("reviews.get(0).getTrainerName() = " + reviews.get(0).getTrainerName());
        System.out.println("reviews.get(0).getPostedDate() = " + reviews.get(0).getPostedDate());
    }
    
    @Test
    void reportdeletetest(){
        Report report1 = new Report();
        report1.setReviewId(0L);
        report1.setUserId("admin");
        report1.setReportReason("스팸");
        reportService.create(report1);

        Report report2 = new Report();
        report2.setReviewId(0L);
        report2.setUserId("admin");
        report2.setReportReason("스팸");
        reportService.create(report2);

        Report report3 = new Report();
        report3.setReviewId(0L);
        report3.setUserId("admin");
        report3.setReportReason("스팸");
        reportService.create(report3);

        reportService.returnReport(1);

        reportService.deleteReport(0L);

        System.out.println(reportService.findReports().size()+"");
    }
}
