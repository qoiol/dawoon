package spring.project.service;

import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;
import spring.project.repository.ReviewRepository;

import java.util.Calendar;
import java.util.List;

@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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

    public void delete(long id){
        reviewRepository.deleteById(id);
    }
}
