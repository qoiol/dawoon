package spring.project.service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.User;
import spring.project.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional //-> Jpa사용시 service 대신 붙여줘야 함.
public class MyPageService {

    private UserRepository userRepository;

    //userId로 유저 객체찾기
    public Optional<User> findById(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
