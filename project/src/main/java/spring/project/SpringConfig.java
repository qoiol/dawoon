package spring.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.project.repository.MemoryReviewRepository;
import spring.project.repository.MemoryUserRepository;
import spring.project.repository.ReviewRepository;
import spring.project.repository.UserRepository;
import spring.project.service.ReviewService;
import spring.project.service.UserService;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
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
}
