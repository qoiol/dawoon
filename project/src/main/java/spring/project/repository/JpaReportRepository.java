package spring.project.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaDelete;
import org.hibernate.query.sqm.tree.delete.SqmDeleteStatement;
import spring.project.domain.Report;

import java.util.List;
import java.util.Optional;

public class JpaReportRepository implements ReportRepository{
    private final EntityManager em;

    public JpaReportRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Report save(Report report) {
        em.persist(report);
        return report;
    }

    @Override
    public void deleteById(Long id) {
        em.remove(em.find(Report.class, id));
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.ofNullable(em.find(Report.class, id));
    }

    @Override
    public List<Report> findAll() {
        return em.createQuery("select r from Report r", Report.class).getResultList();
    }

    @Override
    public void deleteByReviewId(long reviewId) {
        em.createQuery("delete from Report r where r.reviewId = :reviewId").setParameter("reviewId", reviewId).executeUpdate();
    }
}
