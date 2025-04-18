package spring.project.config;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.project.repository.*;
import spring.project.service.*;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewService(reviewRepository(), likeyRepository());
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new JpaReviewRepository(em);
    }

    @Bean
    public LikeyRepository likeyRepository() {
        return new JpaLikeyRepository(em);
    }

    @Bean
    public ReportService reportService() {
        return new ReportService(reportRepository());
    }

    @Bean
    public ReportRepository reportRepository() {
        return new JpaReportRepository(em);
    }

    @Bean
    public WorkoutService workoutService() {
        return new WorkoutService(workoutRepository());
    }

    @Bean
    public WorkoutRepository workoutRepository() {
        return new JpaWorkoutRepository(em);
    }

    @Bean
    public ReservationRepository reservationRepository() {
        return new JpaReservationRepository(em);
    }

    @Bean
    public ReservationService reservationService() {
        return new ReservationService(reservationRepository());
    }

}

