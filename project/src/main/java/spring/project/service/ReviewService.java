package spring.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Likey;
import spring.project.domain.Review;
import spring.project.domain.User;
import spring.project.domain.Workout;
import spring.project.dto.ReviewForm;
import spring.project.dto.ReviewListRequest;
import spring.project.dto.ReviewListResponse;
import spring.project.repository.LikeyRepository;
import spring.project.repository.ReviewRepository;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final LikeyRepository likeyRepository;

    @Value("${page-info.page-size}")
    private Integer pageSize;
    @Value("${page-info.page-block-size}")
    private int pageBlockSize;

    @Transactional
    public long createReview(ReviewForm reviewForm, String userId) {
        Review review = Review.builder()
                .score(reviewForm.getScore())
                .title(reviewForm.getTitle())
                .content(reviewForm.getContent())
                .likeCount(0)
                .postedDate(Calendar.getInstance().getTime())
                .user(User.builder().id(userId).build())
                .workout(Workout.builder().workoutId(reviewForm.getWorkoutId()).build())
                .build();

        reviewRepository.save(review);

        return review.getId();
    }

    public long updateReview(Review review) {
        reviewRepository.update(review);
        return review.getId();
    }

    public List<Review> findReviews(String keyword, Long workoutId, String orderby) {
        return reviewRepository.findAllByWorkoutIdAndTrainerId(keyword, workoutId, orderby);
    }

    public Review findOne(long id) {
        return reviewRepository.findById(id).get();
    }

    public void delete(long id) {
        reviewRepository.deleteById(id);
        likeyRepository.deleteByReviewId(id);
    }

    public String clickLikey(Likey likey) {
        if (likeyRepository.existsByReviewAndUser(likey))
            return "이미 추천했습니다.";
        likeyRepository.save(likey);
        Review review = reviewRepository.findById(likey.getReview().getId()).get();
        review.setLikeCount(review.getLikeCount() + 1);
        reviewRepository.update(review);
        return null;
    }

    public ReviewListResponse findReviews(ReviewListRequest reviewListRequest) {
        List<Review> reviewList = reviewRepository.findReviewsPage(
                reviewListRequest.getKeyword()
                , reviewListRequest.getWorkoutId()
                , reviewListRequest.getOrderby()
                , reviewListRequest.getPageNo()
        );
        int count = reviewRepository.count(reviewListRequest.getKeyword()
                , reviewListRequest.getWorkoutId()
                , reviewListRequest.getOrderby()
        );
        return new ReviewListResponse(reviewList, reviewListRequest.getPageNo(), count, pageSize, pageBlockSize);
    }
}
