package spring.project.repository;

import spring.project.domain.Workout;

import java.util.List;
import java.util.Optional;


public interface WorkoutRepository {

    //저장
    Workout save(Workout workout);

    //삭제
    void delete(Workout workout);

    //운동 이름으로 workout엔티티 찾기
    List<Workout> findByWorkoutName(String workoutName);

    //전체 리스트 가져오기
    List<Workout> findAll();

    //workoutId로 Workout 엔티티 검색
    Optional<Workout> findById(long workoutId);

    //특정 키워드로 Workout 리스트 검색
    List<Workout> findByKeyword(String workoutDifficulty, String workoutType);

}
