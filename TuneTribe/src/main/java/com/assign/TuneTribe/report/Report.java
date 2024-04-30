/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assign.TuneTribe.report;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long reporterId;
    private long reportedId;
    private String explanation;
    private long postId;

    public Report( long reporterId, long reportedId, String explanation) {
       
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.explanation = explanation;
    }
    
    
    
}
