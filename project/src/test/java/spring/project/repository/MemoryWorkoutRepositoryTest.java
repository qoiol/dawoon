package spring.project.repository;

import org.junit.jupiter.api.Test;
import spring.project.domain.Workout;

class MemoryWorkoutRepositoryTest {

    WorkoutRepository repository = new MemoryWorkoutRepository();

    @Test
    public void save(){
        Workout workout = new Workout(1,"수영","지상운동",
                "상","김선생님","김철수");

        repository.save(workout);
        repository.findAll();

    }

}
