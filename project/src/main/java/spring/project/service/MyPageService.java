package spring.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.User;
import spring.project.repository.MyPageRepository;
import spring.project.repository.ReservationRepository;
import spring.project.repository.UserRepository;

import java.util.Optional;

@Transactional //-> Jpa사용시 service 대신 붙여줘야 함.
public class MyPageService {

    private UserRepository userRepository;
    private MyPageRepository myPageRepository;

    @Autowired
    public MyPageService(MyPageRepository myPageRepository){
        this.myPageRepository = myPageRepository;
    }

    public MyPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //userId로 유저 객체찾기
    public Optional<User> findById(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
