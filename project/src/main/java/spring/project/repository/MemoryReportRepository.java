package spring.project.repository;

import org.springframework.stereotype.Repository;
import spring.project.domain.Report;

import java.util.*;

@Repository
public class MemoryReportRepository implements ReportRepository {
    private static Map<Long, Report> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public void save(Report report) {
        report.setId(sequence);
        store.put(sequence++, report);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Report> findAll() {
        return new ArrayList<>(store.values());
    }
}
