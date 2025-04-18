package spring.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Reservation;
import spring.project.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    // userId로 reservation 목록 다 가져오기
    public List<Reservation> findByUserId(String userId) {
        return reservationRepository.findAll(userId);
    }

    // add (신청)
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}
