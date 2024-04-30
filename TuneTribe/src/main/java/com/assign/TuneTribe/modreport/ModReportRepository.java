/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assign.TuneTribe.modreport;

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
public class ModReportRepository {
     @Autowired
      NamedParameterJdbcTemplate template;
     
     List<ModReport>findAll(){
        String query = "select id, mod_explanation, post_id from mod_report";
        return template.query(query,
                (result, rowNum)
                -> new ModReport(result.getLong("id"),
                        result.getLong("post_id"), result.getString(
                        "mod_explanation")));
    }
     
     public ModReport getReportById(long id){
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "select * from mod_report where id=:id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(ModReport.class));
    }
     
     public int saveReport(ModReport report){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("post_id", report.getPostId());
        paramMap.put("mod_explanation", report.getModExplanation());
        String query = "INSERT INTO mod_report(post_id,mod_explanation) VALUES(:post_id, :mod_explanation)";
        
        return template.update(query, paramMap);
    }
     
}
