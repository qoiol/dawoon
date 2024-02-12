package spring.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.project.repository.*;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.UserService;
import spring.project.service.WorkoutService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final ReportRepository reportRepository;

    private final EntityManager em;


    public SpringConfig(ReportRepository reportRepository, EntityManager em) {
        this.reportRepository = reportRepository;
        this.em = em;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
//        return new MemoryUserRepository();
        return new JpaUserRepository(em);
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepository());
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new JpaReviewRepository(em);
    }

    @Bean
    public ReportService reportService() {
        return new ReportService(reportRepository);
    }

    @Bean
    public ReportRepository reportRepository() {
        return new MemoryReportRepository();
    }

    @Bean
    public WorkoutService workoutService() {
        return new WorkoutService(workoutRepository());
    }

    @Bean
    public WorkoutRepository workoutRepository() {
        return new JpaWorkoutRepository(em);
    }
}

