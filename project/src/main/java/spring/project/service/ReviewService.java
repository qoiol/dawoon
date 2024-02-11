package spring.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;
import spring.project.repository.LikeyRepository;
import spring.project.repository.ReviewRepository;

import java.util.List;

//@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final LikeyRepository likeyRepository;

    public ReviewService(ReviewRepository reviewRepository, LikeyRepository likeyRepository) {
        this.reviewRepository = reviewRepository;
        this.likeyRepository = likeyRepository;
    }


    public long createReview(Review review){
        review.setLikeCount(0);
        long id = reviewRepository.save(review).getId();
        return id;
    }

    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }

    public void delete(long id){
        reviewRepository.deleteById(id);
    }
}
