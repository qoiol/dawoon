package spring.project.repository;

import spring.project.domain.Workout;

import java.util.List;

public class MemoryWorkoutRepository implements WorkoutRepository{

    //저장
    @Override
    public Workout add(Workout workout) {
        return null;
    }

    @Override
    public Workout update(Workout workout) {
        return null;
    }

    @Override
    public Workout deleteByWorkoutId(int workoutId) {
        return null;
    }

    @Override
    public Workout findByWorkoutId(int workoutId) {
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
