package spring.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.User;
import spring.project.dto.LoginRequest;
import spring.project.dto.UserJoinRequest;
import spring.project.repository.UserRepository;
import spring.project.util.JwtTokenUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expired-time-ms}")
    private long expiredTimeMs;

    public String join(User user){
        validateDuplicateUser(user.getId());
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public User join2(UserJoinRequest userinfo) {
        userRepository.findById(userinfo.getId()).ifPresent( result -> {
            throw new IllegalStateException("이미 사용 중인 아이디 입니다.");
                });

        User joinUser = User.builder()
                .id(userinfo.getId())
                .userType(userinfo.getUserType())
                .email(userinfo.getEmail())
                .name(userinfo.getName())
                .password(encoder.encode(userinfo.getPassword()))
                .build();

        userRepository.save(joinUser);

        return joinUser;
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

    public User login(User user) {
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

    public String login2(LoginRequest loginRequest) {
        Optional<User> result = userRepository.findById(loginRequest.getId());

        if(result.isEmpty()){
            throw new IllegalStateException(loginRequest.getId() + "는 존재하지 않는 아이디 입니다.");
        }
        result.ifPresent(
                userinfo -> {
                    if(!encoder.matches(loginRequest.getPassword(), userinfo.getPassword())) {
                        throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
                    }
                }
        );


        //토큰 생성
        return JwtTokenUtils.generateToken(loginRequest.getId(), secretKey, expiredTimeMs);
    }
}
