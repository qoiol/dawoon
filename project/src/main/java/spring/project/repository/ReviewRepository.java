package spring.project.repository;

import org.springframework.stereotype.Repository;
import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    long update(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAllByWorkoutIdAndTrainerId(String keyword, Long workoutId, String orderby);
    List<Review> findReviewsPage(String keyword, Long workoutId, String orderby, Integer pageNo);
    List<Review> findAll();
    void deleteById(Long id);
    int count(String keyword, Long workoutId, String orderby);
}
