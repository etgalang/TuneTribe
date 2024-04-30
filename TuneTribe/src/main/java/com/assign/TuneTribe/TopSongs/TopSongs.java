
package com.assign.TuneTribe.TopSongs;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "TopSongs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopSongs {
    
    @Id
    private long id;
    
    private long songOne;
    private long songTwo;
    private long songThree;
    
    public TopSongs(long id){
        this.id = id;
        this.songOne = 1;
        this.songTwo = 1;
        this.songThree = 1;
        
    }
    
}
