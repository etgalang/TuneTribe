
package com.assign.TuneTribe.report;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mauricio
 */
@Service
public class ReportService {
    
    @Autowired
    ReportRepository repo;
    
    /**
     * Get all reports
     * @return the list of reports.
     */
    public List<Report> getAllReports() {
        return repo.findAll();
    }
    
    /**
     * Find one report by ID.
     * @param id
     * @return the report
     */
    public Report getReport(long id){
        return repo.getReportById(id);
    }
    
    /**
     * Save post entry. 
     * @param user 
     */
    public void saveReport(Report report) {
        repo.saveReport(report);
    }
    
}
