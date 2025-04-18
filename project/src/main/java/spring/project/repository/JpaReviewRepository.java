package spring.project.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Review;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class JpaReviewRepository implements ReviewRepository{
    private final EntityManager em;

    @Value("${page-info.page-size}")
    private Integer pageSize;

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public long update(Review review) {
        em.merge(review);
        return review.getId();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(em.createQuery("select r from review r where r.id = :id", Review.class).setParameter("id", id).getSingleResult());
    }

    @Override
    public List<Review> findAllByWorkoutIdAndTrainerId(String keyword, Long workoutId, String orderby) {
        StringBuilder query = new StringBuilder("select r from review r join fetch r.user join fetch r.workout where r.id is not null");

        if(keyword != null && !keyword.isEmpty())
            query.append(" and ((r.title Like '%").append(keyword).append("%') OR (r.content Like '%").append(keyword).append("%'))");
        if(workoutId != null)
            query.append(" and r.workout.workoutId = ").append(workoutId);
        query.append(" order by ").append(orderby).append(" desc");

        return em.createQuery(query.toString(), Review.class).getResultList();
    }

    @Override
    public List<Review> findReviewsPage(String keyword, Long workoutId, String orderby, Integer pageNo) {
        StringBuilder query = new StringBuilder("select r from review r join fetch r.user join fetch r.workout where r.id is not null");

        if(keyword != null && !keyword.isEmpty())
            query.append(" and ((r.title Like '%").append(keyword).append("%') OR (r.content Like '%").append(keyword).append("%'))");
        if(workoutId != null)
            query.append(" and r.workout.workoutId = ").append(workoutId);
        query.append(" order by ").append(orderby).append(" desc");

        return em.createQuery(query.toString(), Review.class)
                .setFirstResult((pageNo-1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Review> findAll() {
        return em.createQuery("select r from review r", Review.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.find(Review.class, id));
    }

    @Override
    public int count(String keyword, Long workoutId, String orderby) {
        StringBuilder query = new StringBuilder("select count(r.id) from review r where r.id is not null");

        if(keyword != null && !keyword.isEmpty())
            query.append(" and ((r.title Like '%").append(keyword).append("%') OR (r.content Like '%").append(keyword).append("%'))");
        if(workoutId != null)
            query.append(" and r.workout.workoutId = ").append(workoutId);

        return em.createQuery(query.toString(), Long.class).getSingleResult().intValue();
    }
}
