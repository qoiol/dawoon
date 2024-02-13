package spring.project.repository;

import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    long update(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAllByWorkoutIdAndTrainerId(String keyword, Long workoutId, String orderby);
    List<Review> findAll();
    void deleteById(Long id);
}
