package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Reservation;
import spring.project.domain.Workout;
import spring.project.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // userId로 reservation 목록 다 가져오기
    public List<Reservation> findByUserId(String userId) {
        return reservationRepository.findAll(userId);
    }

    // add (신청)
    public Reservation add(Reservation reservation) {

        if (validateDuplicateReservation(reservation.getReservationId())) {
            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("이미 같은 예약이 존재합니다.");
        }
    }

    // delete
    public void delete (long reservationId){
        // reservationId 사용하여 해당 Reservation을 DB에서 조회
        Optional<Reservation> optional = reservationRepository.findById(reservationId);

        // 조회된 Reservation이 존재하는지 확인
        if (optional != null) {
            // Workout이 존재할 경우 삭제
            reservationRepository.delete(optional.get());
            // 삭제된 운동 정보를 로그로 출력
            System.out.println("예약 삭제: " + reservationId);
        } else {
            // 조회된 Workout이 없는 경우 예외 발생
            throw new RuntimeException("예약이 존재하지 않습니다. ID: " + reservationId);
        }

    }

    //예약 중복검사
    public boolean validateDuplicateReservation(long reservationId){//reservationId 식별자로 이미 존재하는 Reservation을 조회
        Optional<Reservation> existing = reservationRepository.findById(reservationId);
        //이미 존재하는 Workout이 있으면 중복이니까
        if (existing.isPresent()) {
            return false;
        }
        else { //같은 예약 없음.
            return true;
        }
    }

}

