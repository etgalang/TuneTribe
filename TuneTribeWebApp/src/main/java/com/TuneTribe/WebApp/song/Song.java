
package com.TuneTribe.WebApp.song;


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
@Table(name = "song")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int songId;
    
    private String artist; //might have to store more than one in a string
    private String songName;
    private String coverArtUrl; //spotify provides url for cover of a track
    
}
