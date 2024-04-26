
package com.assign.TuneTribe.post;
import jakarta.persistence.Entity;
import java.io.IOException;
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
public class PostRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    List<Post> findAll() {

        String query = "select post_id, username, caption, post_date, song_id, like_count from post";
        return template.query(query,
                (result, rowNum)
                -> new Post(result.getLong("post_id"),
                        result.getString("username"), result.getString(
                        "caption"), result.getDate("post_date"), 
                        result.getLong("song_id"), result.getInt("like_count")));
    }
    
    public Post getPostById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "post_id", id);
        String query = "select * from song where post_id=:post_id ";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Post.class));
    }
    
    public int savePost(Post post) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", post.getUsername());
        paramMap.put("caption", post.getCaption());
        paramMap.put("post_date", post.getPostDate());
        paramMap.put("song_id", post.getSongId());
        paramMap.put("like_count", post.getLikeCount());
        String query = "INSERT INTO post(username,caption,post_date,song_id,like_count)"
                + " VALUES(:user_id, :caption, :post_date, :song_id, :like_count)";
        return template.update(query, paramMap);
    }
    
    void deleteByPostId (long id) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "post_id", id);
        String query = "delete from post where post_id=:post_id";
        template.update(query, namedParameters);
    }
    
    //add edit post later
    
}
