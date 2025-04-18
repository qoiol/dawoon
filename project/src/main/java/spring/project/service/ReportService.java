package spring.project.service;

import org.springframework.transaction.annotation.Transactional;
import spring.project.domain.Report;
import spring.project.repository.ReportRepository;

import java.util.List;

@Transactional
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void create(Report report){
        reportRepository.save(report);
    }

    public Report findReport(long id){
        Report report = null;
        try{
            report = reportRepository.findById(id).get();
        }finally {
            return report;
        }
    }

    public List<Report> findReports(){
        return reportRepository.findAll();
    }

    public void returnReport(long id){
        reportRepository.deleteById(id);
    }

    public void deleteReport(long reviewId) {
        reportRepository.deleteByReviewId(reviewId);
    }

}
