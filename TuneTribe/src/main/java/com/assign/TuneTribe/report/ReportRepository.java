/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assign.TuneTribe.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mauricio
 */

@Repository
public class ReportRepository {
    @Autowired
    NamedParameterJdbcTemplate template;
    
    List<Report>findAll(){
        String query = "select id, explanation, reported_id, reporter_id, post_id from report";
        return template.query(query,
                (result, rowNum)
                -> new Report(result.getLong("id"),
                        result.getLong("reporter_id"), result.getLong(
                        "reported_id"),result.getString("explanation"), result.getLong("post_id")));
    }
    
    public Report getReportById(long id){
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "select * from report where id=:id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Report.class));
    }
    
    public int saveReport(Report report){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reporter_id", report.getReporterId());
        paramMap.put("reported_id", report.getReportedId());
        paramMap.put("explanation", report.getExplanation());
        paramMap.put("post_id", report.getPostId());
        String query = "INSERT INTO report(reporter_id,reported_id,explanation,post_id) VALUES(:reporter_id, :reported_id, :explanation, :post_id)";
        
        return template.update(query, paramMap);
    }
    
}
