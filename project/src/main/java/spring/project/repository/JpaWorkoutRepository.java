package spring.project.repository;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Workout;

import java.util.List;
import java.util.Optional;

@Transactional
public class JpaWorkoutRepository implements WorkoutRepository{

    private final EntityManager em;

    public JpaWorkoutRepository(EntityManager em){
        this.em = em;
    }

    private static long sequence = 0L;

    @Override
    public Workout save(Workout workout) {
        System.out.println("jpa workoutId가 "+workout.getWorkoutId());
        // merge를 사용하여 엔티티 저장
        return em.merge(workout);
    }

    @Override
    public void delete(Workout workout) {
        em.remove(workout);
    }


    @Override
    public List<Workout> findByWorkoutName(String workoutName) {
        return em.createQuery("select w from Workout w left join fetch w.trainer where w.workoutName = :workoutName",
                Workout.class).setParameter("workoutName", workoutName).getResultList();
    }

    @Override
    public List<Workout> findAll() {
        return em.createQuery("select w from Workout w left join fetch w.trainer", Workout.class).getResultList();
    }

    @Override
    public Optional<Workout> findById(long workoutId) {
        Workout workout = em.find(Workout.class, workoutId);
        return Optional.ofNullable(workout);
    }

    @Override
    public List<Workout> findByKeyword(String workoutDifficulty, String workoutType) {
        return em.createQuery("select w from Workout w where w.workoutDifficulty = :workoutDifficulty " +
                "and w.workoutType = :workoutType", Workout.class).setParameter("workoutDifficulty", workoutDifficulty)
                .setParameter("workoutType", workoutType).getResultList();
    }

    private synchronized Long generateWorkoutId() {
        return ++sequence;
    }
}
