
package com.assign.TuneTribe.TopSongs;

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
public class TopSongsRepository {

    @Autowired
    NamedParameterJdbcTemplate template;
    
    public int saveTopSongs (TopSongs topSongs){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", topSongs.getId());
        paramMap.put("song_one", topSongs.getSongOne());
        paramMap.put("song_two", topSongs.getSongTwo());
        paramMap.put("song_three", topSongs.getSongThree());
        String query = "INSERT INTO top_songs(id,song_one,song_two,song_three)"
                + " VALUES(:id, :song_one, :song_two, :song_three)";
        return template.update(query, paramMap);
    }

    public TopSongs findByTopSongsById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "SELECT * FROM top_songs WHERE id=:id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(TopSongs.class));
        
    }

    public void updateTopSong(long id, int selection, long songId ) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        String query ="";
        paramMap.addValue("id", id);
        paramMap.addValue("songId", songId);
        if (selection == 1){
            query = "UPDATE top_songs SET song_one=:songId WHERE id=:id";
        }
        else if(selection == 2){
            query = "UPDATE top_songs SET song_two=:songId WHERE id=:id";
        }
        else{
            query = "UPDATE top_songs SET song_three=:songId WHERE id=:id";
        }    

        template.update(query, paramMap);
    }

}
