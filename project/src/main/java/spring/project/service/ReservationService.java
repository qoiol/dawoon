package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Reservation;
import spring.project.repository.ReservationRepository;

import java.util.List;

@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    // userId로 reservation 목록 다 가져오기
    public List<Reservation> findByUserId(String userId) {
        return reservationRepository.findAll(userId);
    }

    // add (신청)
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

//    // delete
//    public void delete(long reservationId) {
//        reservationRepository.delete(reservationId);
//    }


}
