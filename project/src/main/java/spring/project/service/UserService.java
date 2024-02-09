package spring.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.User;
import spring.project.repository.MemoryUserRepository;
import spring.project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // <- jpa 사용하면 service 대신 붙여줘야함
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String join(User user){
        validateDuplicateUser(user.getId());
        userRepository.save(user);
        return user.getId();
    }

    public void validateDuplicateUser(String id){
        userRepository.findById(id).ifPresent( result -> {
            throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
        } );
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(String id){
        return userRepository.findById(id);
    }

    public User login(User user){
        Optional<User> result = userRepository.findById(user.getId());

        if(!result.isPresent()){
            throw new IllegalStateException(user.getId() + "는 존재하지 않는 아이디 입니다.");
        }
        result.ifPresent(
                u -> {
                    if(!u.getPassword().equals(user.getPassword())){
                        throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
                    }
                }
        );
        return result.get();
    }
}
