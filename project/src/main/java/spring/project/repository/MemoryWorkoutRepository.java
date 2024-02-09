package spring.project.repository;

import org.springframework.stereotype.Repository;
import spring.project.domain.Workout;

import java.util.*;

@Repository
public class MemoryWorkoutRepository implements WorkoutRepository {

    private static Map<Long, Workout> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Workout save(Workout workout) {
        if(workout.getWorkoutId() == 0L) {
            workout.setWorkoutId(generateId());
        }
        store.put(workout.getWorkoutId(), workout);
        return workout;
    }

    @Override
    public void delete(Workout workout) {
        store.remove(workout.getWorkoutId());
    }

    @Override
    public Workout update(Workout workout) {
        store.put(workout.getWorkoutId(), workout);
        return workout;
    }

    @Override
    public Optional<Workout> findById(long workoutId) {
        Long id = workoutId;
        return Optional.ofNullable(store.get(id));
    }


//    @Override
//    public List<Workout> findByUserId(String userId) {
//        Reservation reservation = new Reservation();
//
//        List<Workout> workouts = new ArrayList<>();
//        for(Workout workout : store.values()) {
//            if(workout.get().equals(userId)){
//                workouts.add(workout);
//            }
//        }
//        return workouts;
//    }

    @Override
    public List<Workout> findAll() {
        return new ArrayList<>(store.values());
    }

    private synchronized Long generateId() {
        return ++sequence;
    }
}
