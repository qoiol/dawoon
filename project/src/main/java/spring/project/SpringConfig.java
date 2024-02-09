package spring.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.project.repository.MemoryUserRepository;
import spring.project.repository.UserRepository;
import spring.project.repository.WorkoutRepository;
import spring.project.service.UserService;
import spring.project.service.WorkoutService;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, WorkoutRepository workoutRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
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
    public WorkoutService workoutService(){
        return new WorkoutService(workoutRepository);
    }

}
