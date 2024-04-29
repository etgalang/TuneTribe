
package com.assign.TuneTribe.song;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mauricio
 */

@Service
public class SongService {
    
    @Autowired
    SongRepository repo;
    
     /**
     * Get all songs
     * @return the list of songs.
     */
    public List<Song> getAllSongs() {
        return repo.findAll();
    }
    
    /**
     * Find one song by ID.
     * @param id
     * @return the song
     */
    public Song getSong(long id) {
        return repo.getSongById(id);
    }
    
    public Song getSongByName(String name) {
        return repo.getSongByName(name);
    }
    
    /**
     * Delete song by ID.
     * @param id 
     */
    public void deleteSong(long id) {
        repo.deleteSongById(id);
    }
    
    /**
     * Save song entry. 
     * @param user 
     */
    public void saveSong(Song song) {
        repo.saveSong(song);
    }
    
    /**
     * Update existing song.
     * @param user 
     */
    public void updateSong(Song song) {
        repo.updateSong(song);
    }
    
    public Song getRecommendation(){
        return repo.recommendSong();
}
    
    public Song searchSong(String query){      
        return repo.searchSong(query);
    }
    
}
