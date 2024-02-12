package spring.project.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.project.domain.Workout;

import java.util.*;

//@Repository
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
    public List<Workout> findByWorkoutName(String workoutName) {
        return null;
    }

    @Override
    public Optional<Workout> findById(long workoutId) {
        Long id = workoutId;
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Workout> findByKeyword(String workoutDifficulty, String workoutType) {
        List<Workout> foundWorkouts = new ArrayList<>();

        // 저장된 모든 운동을 반복하여 키워드와 일치하는 운동을 찾습니다.
        for (Workout workout : store.values()) {
            // 운동 이름 또는 다른 속성에서 키워드를 검색하고 일치하는 경우 리스트에 추가합니다.
            if (workout.getWorkoutDifficulty().contains(workoutDifficulty) &&
                    (workout.getWorkoutType().contains(workoutType)))
                foundWorkouts.add(workout);

        }
        return foundWorkouts;
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
