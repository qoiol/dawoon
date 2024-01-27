package spring.project.repository;

import spring.project.domain.Workout;

import java.util.List;

public class MemoryWorkoutRepository implements WorkoutRepository{

    @Override
    public Workout save(Workout workout) {
        workout.setWorkoutId(workout.getWorkoutId());
        workout.setWorkoutName(workout.getWorkoutName());
        workout.setWorkoutType(workout.getWorkoutType());
        workout.setWorkoutDifficulty(workout.getWorkoutDifficulty());
        workout.setTrainerId(workout.getTrainerId());
        workout.setTrainerName(workout.getTrainerName());
        return workout;
    }

    @Override
    public Workout update(Workout workout) {
        return null;
    }

    @Override
    public Workout delete(Workout workout) {
        return null;
    }

    @Override
    public List<Workout> findAll() {
        return null;
    }

    @Override
    public List<Workout> findByUserId(String userId) {
        return null;
    }

    @Override
    public List<Workout> findByKeyword(String keyword) {
        return null;
    }
}
