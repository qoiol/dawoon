package spring.project.service;

import org.springframework.stereotype.Service;
import spring.project.domain.Report;
import spring.project.repository.ReportRepository;

@Service
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

    public void reportCancel(long id){
        reportRepository.deleteById(id);
    }


}
