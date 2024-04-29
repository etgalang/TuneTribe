
package com.assign.TuneTribe.TopSongs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mauricio
 */

@Service
public class TopSongsService {
    
    @Autowired
    TopSongsRepository repo;
    
    public void saveTopSongs (TopSongs topSongs){

        repo.saveTopSongs(topSongs);
        
    }

    public TopSongs findByTopSongsById(Long id) {
        
        return repo.findByTopSongsById(id);
        
    }

    public void updateTopSong(long id, int selection, long songId ) {
        repo.updateTopSong(id, selection, songId);
    }
    
}
