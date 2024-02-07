package spring.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.project.repository.*;
import spring.project.service.ReportService;
import spring.project.service.ReviewService;
import spring.project.service.UserService;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, ReviewRepository reviewRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.reportRepository = reportRepository;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository);
    }

    @Bean
    public UserRepository userRepository(){
        return new MemoryUserRepository();
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepository);
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new MemoryReviewRepository();
    }

    @Bean
    public ReportService reportService() {
        return new ReportService(reportRepository);
    }

    @Bean
    public ReportRepository reportRepository(){
        return new MemoryReportRepository();
    }
}
