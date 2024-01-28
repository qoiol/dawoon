package spring.project.repository;

import org.hibernate.jdbc.Work;
import spring.project.domain.Workout;

import java.util.List;

public interface WorkoutRepository {

    Workout add(Workout workout);
    Workout update(Workout workout);
    Workout deleteByWorkoutId(int workoutId);

    //workoutId로 중복검사 위해서
    Workout findByWorkoutId(int workoutId);
    List<Workout> findAll(); //전체 리스트
    List<Workout> findByUserId(String userId); //MyPage에서 가져다 쓸거 (userId로 검색)
    List<Workout> findByKeyword(String keyword); //keyword 검색할 거



}
