package spring.project.repository;

import spring.project.domain.Reservation;

import java.util.List;

public interface ReservationRepository {

    //저장
    Reservation save(Reservation reservation);

    //reservationId로 전체 reservation 리스트 가져오기
    List<Reservation> findAll(String userId);

//    //삭제
//    void delete(long reservationId);
//
//    //수정
//    Reservation update(Reservation reservation);
//
//    //userId로 Reservation 리스트 가져오기
//    List<Reservation> findByUserId(String userId);
}

