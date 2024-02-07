package spring.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.project.domain.Workout;
import spring.project.service.WorkoutService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WorkoutRepositoryTest {

    @Autowired
    private WorkoutService workoutService;

    @Test
    public void testAdd(){
        Workout workout = new Workout();

        workout.setWorkoutId(1); // WorkoutId 설정
        workout.setWorkoutType("러닝");
        workout.setWorkoutDifficulty("초보자");
        workout.setTrainerId("TR123");
        workout.setTrainerName("홍길동");

        // Then
        List<Workout> workouts = workoutService.getAllWorkoutList();
        assertNotNull(workouts); // Workout 리스트가 null이 아닌지 확인
        assertEquals(1, workouts.size()); // 저장된 Workout 개수가 1개인지 확인
        Workout savedWorkout = workouts.get(0);
        assertEquals(1, savedWorkout.getWorkoutId()); // 저장된 Workout의 Id가 설정한 값과 일치하는지 확인
        assertEquals("러닝", savedWorkout.getWorkoutType()); // 저장된 Workout의 Type이 설정한 값과 일치하는지 확인
        assertEquals("초보자", savedWorkout.getWorkoutDifficulty()); // 저장된 Workout의 Difficulty가 설정한 값과 일치하는지 확인
        assertEquals("TR123", savedWorkout.getTrainerId()); // 저장된 Workout의 TrainerId가 설정한 값과 일치하는지 확인
        assertEquals("홍길동", savedWorkout.getTrainerName()); // 저장된 Workout의 TrainerName이 설정한 값과 일치하는지 확인
    }

}
