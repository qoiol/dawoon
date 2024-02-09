package spring.project.repository;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;
import spring.project.domain.User;

import java.util.*;

//@Repository <- jpa repository 이용하기 위해 삭제
public class MemoryUserRepository implements UserRepository {
    public static Map<String, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
