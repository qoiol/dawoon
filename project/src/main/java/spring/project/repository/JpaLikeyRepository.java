package spring.project.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TransactionRequiredException;
import spring.project.domain.Likey;

import java.util.List;

public class JpaLikeyRepository implements LikeyRepository {
    private final EntityManager em;

    public JpaLikeyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Likey save(Likey likey) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
        em.persist(likey);
        return likey;
    }

    @Override
    public List<Likey> findByReviewId(Long reviewId) {
        em.createQuery("select l from Likey l where l.review.id = :reviewId").setParameter("reviewId", reviewId).getResultList();
        return null;
    }

    @Override
    public List<Likey> findByUserId(String userId) {
        return null;
    }

    @Override
    public Long countByReviewId(Long reviewId) {
        return null;
    }

    @Override
    public void deleteByReviewId(long reviewId) {

    }

    @Override
    public void delete(Likey likey) {

    }

    public boolean existsByReviewAndUser(Likey likey) {
        int result = em.createQuery("select l from Likey l where l.review.id = :reviewId and l.user.id = :userId")
                .setParameter("reviewId", likey.getReview().getId())
                .setParameter("userId", likey.getUser().getId())
                .getFirstResult();

        return result == 1;

    }
}
