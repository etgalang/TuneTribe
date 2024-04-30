
package com.assign.TuneTribe.modreport;

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
public class ModReportService {
    @Autowired
    ModReportRepository repo;
    
    /**
     * Get all reports
     * @return the list of reports.
     */
    public List<ModReport> getAllReports() {
        return repo.findAll();
    }
    
    /**
     * Find one report by ID.
     * @param id
     * @return the report
     */
    public ModReport getReport(long id){
        return repo.getReportById(id);
    }
    
    /**
     * Save report entry. 
     * @param ModReport 
     */
    public void saveReport(ModReport report) {
        repo.saveReport(report);
    }
}
