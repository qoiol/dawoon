package spring.project.repository;

import spring.project.domain.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    Report save(Report report);
    void deleteById(Long id);
    Optional<Report> findById(Long id);
    List<Report> findAll();
    void deleteByReviewId(long reviewId);
}
