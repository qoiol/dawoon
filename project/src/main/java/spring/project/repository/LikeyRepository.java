package spring.project.repository;

import spring.project.domain.Likey;

import java.util.List;

public interface LikeyRepository {
    Likey save(Likey likey);
    List<Likey> findByReviewId(Long reviewId);
    List<Likey> findByUserId(String userId);
    Long countByReviewId(Long reviewId);
    void deleteByReviewId(long reviewId);
    void delete(Likey likey);
    boolean existsByReviewAndUser(Likey likey);
}
