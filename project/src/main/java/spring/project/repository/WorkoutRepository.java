package spring.project.repository;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.project.domain.Workout;

import java.util.List;
import java.util.Optional;


public interface WorkoutRepository {

    //저장
    Workout save(Workout workout);

    //삭제
    void delete(Workout workout);

    //수정
    Workout update(Workout workout);

    //전체 리스트 가져오기
    List<Workout> findAll();

    //workoutId로 Workout 엔티티 검색
    Optional<Workout> findById(long workoutId);


//    //특정 키워드로 Workout 리스트 검색
//    //@Query("SELECT w FROM Workout w WHERE keyword LIKE %:keyword%")
//    List<Workout> findWorkoutByKeyword(String keyword);

}
