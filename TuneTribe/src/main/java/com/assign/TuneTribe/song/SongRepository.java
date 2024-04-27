
package com.assign.TuneTribe.song;
import jakarta.persistence.Entity;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;


/**
 *
 * @author Mauricio
 */
@Repository
public class SongRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    //spotify api start
    private static final String accessToken = "BQAX8AfHY7lRHEG1PUUEJmwTIKCFkfWsGKOgudfMON1i1faMOKXssW8IUpB3WTWBpon1_xATuAqY67tGrzNwx9UqEFGyihQDKXdx6tCCso9fkuStfJg";

    
    List<Song> findAll() {

        String query = "select id, artist, cover_url, name, spotify_id from song";
        return template.query(query,
                (result, rowNum)
                -> new Song(result.getLong("id"),
                        result.getString("name"), result.getString(
                        "artist"), result.getString("cover_url"), 
                        result.getString("spotify_id")));
    }
    
    public Song getSongById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "select * from song where id=:id ";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Song.class));
    }
    
    public Song getSongByName(String name){
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "name", name);
        String query = "select * from song where name=:name ";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Song.class));
    }
    
    public int saveSong(Song song) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("artist", song.getArtist());
        paramMap.put("cover_url", song.getCoverUrl());
        paramMap.put("name", song.getName());
        paramMap.put("spotify_id", song.getSpotifyId());
        String query = "INSERT INTO song(artist,cover_url,name,spotify_id) VALUES(:artist, :cover_url, :name, :spotify_id)";
        return template.update(query, paramMap);
    }
    
    void deleteSongById(long id) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "delete from song where id=:id";
        template.update(query, namedParameters);
    }
    
    void updateSong(Song song) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", song.getId());
        paramMap.put("artist", song.getArtist());
        paramMap.put("cover_url", song.getCoverUrl());
        paramMap.put("name", song.getName());
        paramMap.put("spotify_id", song.getSpotifyId());
        String query = "update song set artist=:artist, cover_url=:cover_url, name=:name, spotify_id=:spotify_id where id=:id ";
        template.update(query, paramMap);
        
    }
    
    Song recommendSong(){ //get a song id to seed recommendation
        String trackSeed = "35xvhWIZMpsDcJxr14Ukbx";
        try {
             SpotifyApi spotifyApi = new SpotifyApi.Builder()
                    .setAccessToken(accessToken)
                    .build();
            GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(1)
                    //          .market(CountryCode.SE)
                    //          .max_popularity(50)
                    //          .min_popularity(10)
                    //          .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
                    //          .seed_genres("electro")
                    .seed_tracks(trackSeed)
                    //          .target_popularity(20)
                    .build();

            Recommendations recommendations = getRecommendationsRequest.execute();
            //end of spotify api and begin parsing of the information
            
            Song temp = new Song();
            String artist="";
            
            temp.setName(recommendations.getTracks()[0].getName().toString());
            for (int i = 0; i < recommendations.getTracks()[0].getArtists().length; i++ ){
                artist += recommendations.getTracks()[0].getArtists()[i].getName() + ", ";
            }
            artist = artist.substring(0, artist.length()-2);
            temp.setArtist(artist);
            temp.setSpotifyId(recommendations.getTracks()[0].getId().toString());
            temp.setCoverUrl(recommendations.getTracks()[0].getAlbum().getImages()[1].getUrl());
            
            
            //printing to system the song for test purposes
            /*
            System.out.println(temp.getName());
            System.out.println(temp.getArtist());
            System.out.println(temp.getSpotifyId());
            System.out.println(temp.getCoverUrl());
            */
            //save song and return it to user by searching for the song
            //this.saveSong(temp);
            return temp;
            
            

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
