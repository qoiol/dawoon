package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.project.domain.Workout;
import spring.project.repository.WorkoutRepository;

import java.util.List;
import java.util.Optional;

@Service
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

        System.out.println("Service workoutId11111가 "+workout.getWorkoutId());
        workoutRepository.save(workout);
        System.out.println("Service workoutId2222가 "+workout.getWorkoutId());
//        if(validateDuplicateWorkout(workout.getWorkoutId())) {
//            workoutRepository.save(workout);
//        } else {
//            throw new RuntimeException("이미 같은 운동이 존재합니다.");
//        }
    }

    //workoutId로 Workout 엔티티 하나 가져오기
    public Optional<Workout> findById(long workoutId){
        Optional<Workout> foundWorkout = workoutRepository.findById(workoutId);
        return foundWorkout;
    }

    //운동 삭제
    public void deleteWorkout(long workoutId){
        // workoutId를 사용하여 해당 Workout을 DB에서 조회
        Optional<Workout> optionalWorkout = workoutRepository.findById(workoutId);

        // 조회된 Workout이 존재하는지 확인
        if (optionalWorkout.isPresent()) {
            Workout workout = optionalWorkout.get();
            // Workout이 존재할 경우 삭제
            workoutRepository.delete(workout);
            // 삭제된 운동 정보를 로그로 출력
            System.out.println("운동 삭제: " + workout);
        } else {
            // 조회된 Workout이 없는 경우 예외 발생
            throw new RuntimeException("운동이 존재하지 않습니다. ID: " + workoutId);
        }
    }

//    //운동 예약 메소드
//    public void reserveWorkout(long workoutId) {
//        //로직 구현.. 운동 예약, DB에 저장
//        Workout workout = workoutRepository.findById(workoutId)
//                .orElseThrow(() -> new RuntimeException("운동 정보를 찾을 수 없습니다."));
//
//        //운동정보 예약
//        //예약에 필요한 추가적인 처리
//    }

    //운동 중복신청 검사 (WorkoutId(운동 식별자)로 겹치는지 검사)
    public boolean validateDuplicateWorkout(long workoutId){
        //workoutId 식별자로 이미 존재하는 Workout을 조회
        Optional<Optional<Workout>> existingWorkout =
                Optional.ofNullable(workoutRepository.findById(workoutId));

        //이미 존재하는 Workout이 있으면 중복이니까
        if (existingWorkout.isPresent()) {
            return false;
        }
        else { //같은 운동 없음.
            return true;
        }
    }

//    // userId로 Workout 리스트 가져오기
//    public List<Workout> getWorkoutsByUserId(String userId) {
//        return workoutRepository.findByUserId(userId);
//    }

    // 특정 키워드로 Workout 리스트 검색
    public List<Workout> findByKeyword(String workoutDifficulty, String workoutType) {
        return workoutRepository.findByKeyword(workoutDifficulty, workoutType);
    }

}
