package spring.project.repository;

import org.springframework.stereotype.Repository;
import spring.project.domain.Review;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//@Repository
public class MemoryReviewRepository implements ReviewRepository {
    private static Map<Long, Review> store = new HashMap<>();
    private static long seq = 0L;
    @Override
    public void save(Review review) {
        review.setId(++seq);
        review.setPostedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        store.put(review.getId(), review);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
