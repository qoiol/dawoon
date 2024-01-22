package spring.project.repository;

import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    public Review findById(Long id);
    public List<Review> findAll();
    public Optional<List<Review>> findByWorkoutId();
}
