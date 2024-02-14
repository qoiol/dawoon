package spring.project.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Reservation;

import java.util.List;

@Repository
@Transactional
public class JpaReservationRepository implements ReservationRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaReservationRepository(EntityManager em){
        this.em = em;
    }

    private static long sequence = 0L;

    @Override
    public Reservation save(Reservation reservation) {
        //sequence 넣어주고
        reservation.setReservationId(generateReservationId());
        //System.out.println("reservationId is ---:"+generateReservationId());
        return em.merge(reservation);
    }

    @Override
    public List<Reservation> findAll(String userId) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.userId = :userId", Reservation.class)
                .setParameter("userId", userId)
                .getResultList();
    }

//    public List<Reservation> findAll() {
//        return em.createQuery("select r from Reservation r", Reservation.class).getResultList();
//    }

    //    @Override
//    public void delete(long reservationId) {
//
//    }
//
//    @Override
//    public Reservation update(Reservation reservation) {
//        return null;
//    }
//
//    @Override
//    public List<Reservation> findByUserId(String userId) {
//        return null;
//
//    }
//
    private synchronized Long generateReservationId() {
        return ++sequence;
    }
}