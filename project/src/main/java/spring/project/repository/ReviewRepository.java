package spring.project.repository;

import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAllByWorkoutIdAndTrainerId();
    List<Review> findAll();
    void deleteById(Long id);
}
