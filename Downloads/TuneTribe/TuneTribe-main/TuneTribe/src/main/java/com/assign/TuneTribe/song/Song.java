
package com.assign.TuneTribe.song;

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
    private long id;
    
    private String name;
    private String artist;
    private String coverUrl;
    private String spotifyId;
    
    /*
    public Song(long id, String name, String artist, String coverUrl, String spotifyId){
        this.id = id;
        this.name = name;
        this.artist= artist;
        this.coverUrl = coverUrl;
        this.spotifyId = spotifyId;
    }*/
}
