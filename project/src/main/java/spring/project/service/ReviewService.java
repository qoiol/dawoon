package spring.project.service;

import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;
import spring.project.repository.LikeyRepository;
import spring.project.repository.ReviewRepository;

import java.util.Calendar;
import java.util.List;

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
        review.setPostedDate(Calendar.getInstance().getTime());
        reviewRepository.save(review);

        return review.getId();
    }

    public List<Review> findReviews(){
        return reviewRepository.findAllByWorkoutIdAndTrainerId();
    }

    public Review findOne(long id){
        return reviewRepository.findById(id).get();
    }

    public void delete(long id){
        reviewRepository.deleteById(id);
    }
}
