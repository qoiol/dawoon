package spring.project.repository;

import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    void save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAll();
    void deleteById(Long id);
}
