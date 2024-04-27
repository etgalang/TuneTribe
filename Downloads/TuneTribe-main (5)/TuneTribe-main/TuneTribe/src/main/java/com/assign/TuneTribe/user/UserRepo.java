
package com.assign.TuneTribe.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Entity;

/**
 *
 * @author Mauricio
 */

@Repository
public class UserRepo {
    @Autowired
    NamedParameterJdbcTemplate template;
    
    List<User> findAll() {

        String query = "select id, user_name, email, role, password from user";
        return template.query(query,
                (result, rowNum)
                -> new User(result.getLong("id"),
                        result.getString("user_name"), result.getString(
                        "user_email"), 
                        result.getString("user_password"), result.getString("userfname"), 
                result.getString("userlname"), result.getString("role"), 
                        result.getInt("top_song")));
    }
    
    public User getUserByUsername(String username) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "user_name", username); //not working
        String query = "SELECT * FROM user WHERE user_name=:user_name";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(User.class));
    }//
    
    /*
    public int saveUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", user.getUserName());
        paramMap.put("email", user.getEmail());
        paramMap.put("role", user.getRole());
        paramMap.put("password", user.getPassword());
        String query = "INSERT INTO user(user_name,email,role,password) VALUES(:user_name, :email, :role, :password)";
        return template.update(query, paramMap);
    }
    */
    
    void deleteUserById(long id) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "delete from user where id=:id";
        template.update(query, namedParameters);
    }
    
    
    void updateUser(User user) { //keep or change to change song?

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getId());
        paramMap.put("user_name", user.getUserName());
        paramMap.put("email", user.getUserEmail());
        paramMap.put("role", user.getRole());
        paramMap.put("password", user.getUserPassword());
        String query = "update user set user_name=:user_name, email=:email, role=:role, password=:password where id=:id ";
        template.update(query, paramMap);
    }
    
}
