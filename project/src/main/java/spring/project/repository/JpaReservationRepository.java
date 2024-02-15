package spring.project.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Reservation;

import java.util.List;
import java.util.Optional;

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
        reservation.setReservationId(generateReservationId());
        return em.merge(reservation);
    }

    @Override
    public List<Reservation> findAll(String userId) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.userId = :userId",
                        Reservation.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<Reservation> findById(long reservationId) {
//        return em.createQuery("SELECT r FROM Reservation r " +
//                "WHERE r.reservationId = :reservationId", Reservation.class)
//                .setParameter("reservationId", reservationId)
//                .getSingleResult();
        try {
            Reservation reservation = em.createQuery("SELECT r FROM Reservation r " +
                            "WHERE r.reservationId = :reservationId", Reservation.class)
                    .setParameter("reservationId", reservationId)
                    .getSingleResult();
            return Optional.of(reservation);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Reservation reservation) {
        em.remove(reservation);
    }

    private synchronized Long generateReservationId() {
        return ++sequence;
    }
}