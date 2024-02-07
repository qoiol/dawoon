package spring.project.repository;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.project.domain.Workout;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    //workoutId로 검색
    Workout findByWorkoutId(int workoutId);

    //userId로 검색해서 Mypage에서 쓸거
    List<Workout> findByUserId(String userId);

    //특정 키워드로 Workout 리스트 검색
    List<Workout> findByKeyword(String keyword);



}
