package spring.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.project.domain.Workout;

class MemoryWorkoutRepositoryTest {

    WorkoutRepository repository = new MemoryWorkoutRepository();

    @Test
    public void add(){
        Workout workout = new Workout(1,"수영","강",
                "miel","김선생님");

        repository.add(workout);
        Workout workout1 = repository.findByWorkoutId(1);
        String trainer = workout1.getTrainerId();

        Assertions.assertEquals("miel", trainer);
    }

}
