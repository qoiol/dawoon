package spring.project.repository;

import spring.project.domain.Likey;

import java.util.List;

public interface LikeyRepository {
    void save(Likey likey);
    List<Likey> findByReviewId(Long reviewId);
    List<Likey> findByUserId(String userId);
    Long countByReviewId(Long reviewId);
}
