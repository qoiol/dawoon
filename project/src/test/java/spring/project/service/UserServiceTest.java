package spring.project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.project.domain.User;
import spring.project.repository.MemoryUserRepository;
import spring.project.repository.UserRepository;

public class UserServiceTest {
    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }
    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    void join(){
        User user = new User();
        user.setUserType("user");
        user.setEmail("aaaa@bbb.ccc");
        user.setPassword("password");
        user.setId("testuser");
        user.setName("테스트");
        userService.join(user);
    }
}
