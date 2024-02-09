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
//    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReportRepository reportRepository;
    private final LikeyRepository likeyRepository;
//    private final WorkoutRepository workoutRepository;

    private final EntityManager em;
    private final DataSource dataSource;

    public SpringConfig(ReviewRepository reviewRepository, ReportRepository reportRepository, LikeyRepository likeyRepository, EntityManager em, DataSource dataSource) {
        this.reviewRepository = reviewRepository;
        this.reportRepository = reportRepository;
        this.likeyRepository = likeyRepository;
        this.em = em;
        this.dataSource = dataSource;
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
        return new ReviewService(reviewRepository, likeyRepository);
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new MemoryReviewRepository();
    }

    @Bean
    public LikeyRepository likeyRepository() {
        return new MemoryLikeyRepository();
    }

    @Bean
    public ReportService reportService() {
        return new ReportService(reportRepository);
    }

    @Bean
    public ReportRepository reportRepository() {
        return new MemoryReportRepository();
    }

}
