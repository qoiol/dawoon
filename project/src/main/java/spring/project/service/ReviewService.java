package spring.project.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Likey;
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

    public long createReview(Review review) {
        review.setLikeCount(0);
        review.setPostedDate(Calendar.getInstance().getTime());
        reviewRepository.save(review);

        return review.getId();
    }

    public long updateReview(Review review) {
        reviewRepository.update(review);
        return review.getId();
    }

    public List<Review> findReviews() {
        return reviewRepository.findAllByWorkoutIdAndTrainerId();
    }

    public Review findOne(long id) {
        return reviewRepository.findById(id).get();
    }

    public void delete(long id) {
        reviewRepository.deleteById(id);
        likeyRepository.deleteByReviewId(id);
    }

    public String clickLikey(Likey likey) {
        if(likeyRepository.findById(likey.getLikeyId()) != null)
            return "이미 추천했습니다.";
        likeyRepository.save(likey);
        Review review = reviewRepository.findById(likey.getLikeyId().getReviewId()).get();
        review.setLikeCount(review.getLikeCount()+1);
        reviewRepository.update(review);
        return null;
    }

}
