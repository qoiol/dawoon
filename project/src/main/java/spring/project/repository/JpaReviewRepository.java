package spring.project.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

@Transactional
public class JpaReviewRepository implements ReviewRepository{
    private final EntityManager em;

    public JpaReviewRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(em.createQuery("select new spring.project.domain.Review(r.id, r.userId, r.workoutId, r.title, r.content, r.score, r.likeCount, r.postedDate, " +
                "w.workoutName, u.name) from review r, workout w, userinfo u where r.id = :id and w.workoutId = r.workoutId and w.trainerId = u.id", Review.class).getSingleResult());
    }

    @Override
    public List<Review> findAllByWorkoutIdAndTrainerId() {
        return em.createQuery("select new spring.project.domain.Review(r.id, r.userId, r.workoutId, r.title, r.content, r.score, r.likeCount, r.postedDate, " +
                "w.workoutName, u.name) from review r, Workout w, userinfo u where w.workoutId = r.workoutId and w.trainerId = u.id", Review.class).getResultList();
    }

    @Override
    public List<Review> findAll() {
        return em.createQuery("select r from review r", Review.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.find(Review.class, id));
    }
}
