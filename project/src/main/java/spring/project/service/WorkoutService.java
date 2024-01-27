package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.project.domain.Workout;
import spring.project.repository.WorkoutRepository;

import java.util.List;
import java.util.Optional;

public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    //전체 workout 리스트 가져오기
    public List<Workout> getAllWorkoutList(){
        return workoutRepository.findAll();
    }

    //운동 추가, 신청
    public void addWorkout(Workout workout){
        if(validateDuplicateWorkout(workout))
            workoutRepository.add(workout);
    }

    //운동 업데이트
    public void updateWorkout(Workout workout){
        workoutRepository.update(workout);
    }

    //운동 삭제
    public void deleteWorkout(int workoutId){
        workoutRepository.deleteByWorkoutId(workoutId);
    }

    //운동 중복신청 검사 (WorkoutId(운동 식별자)로 겹치는지 검사)
    public boolean validateDuplicateWorkout(Workout workout){
        Optional<Workout> existingWorkout =
                Optional.ofNullable(workoutRepository.findByWorkoutId(workout.getWorkoutId()));

        if (existingWorkout.isPresent()) {
            throw new RuntimeException("이미 같은 운동이 존재합니다.");
        }
        else { //같은 운동 없음.
            return true;
        }
    }
}
