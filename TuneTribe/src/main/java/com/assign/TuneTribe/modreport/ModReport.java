
package com.assign.TuneTribe.modreport;

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
@Table(name = "modReport")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private long postId;
    
    private String modExplanation;

    private boolean banned;
}
