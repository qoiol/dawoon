package spring.project.repository;

import spring.project.domain.Workout;

import java.util.List;

public interface WorkoutRepository {

    Workout save(Workout workout);
    Workout update(Workout workout);
    Workout delete(Workout workout);

    List<Workout> findAll(); //전체 리스트
    List<Workout> findByUserId(String userId); //MyPage에서 가져다 쓸거
    List<Workout> findByKeyword(String keyword); //keyword 검색할 거


}
