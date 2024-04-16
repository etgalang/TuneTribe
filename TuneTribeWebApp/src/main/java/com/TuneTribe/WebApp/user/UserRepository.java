
package com.TuneTribe.WebApp.user;

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
public class UserRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;
    
    List<User> findAll(){
        String query = "Enter users userName or name";
        return template.query(query,(result, rowNum) 
                -> new User (result.getString("user_name"),
                result.getString("email"), result.getString("password"),
                result.getString("f_name"),result.getString("l_name")));
    }
    
    public User getUserById(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "select * from user where user_id=:id "; // use SQl name for fir first id, and 
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(User.class));
    }
    
    public int saveUser(User user) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", user.getUserName());
        paramMap.put("email", user.getEmail());
        paramMap.put("password", user.getPassword());
        paramMap.put("fname", user.getFName());
        paramMap.put("lname", user.getLName());
        String query = "INSERT INTO user(user_name, email, password, f_name, l_name) VALUES(:user_name, :email, :password, :f_name, l_name)";
        return template.update(query, paramMap);
    }//String userName, String email, String password, String fName, String lName
    
    void deleteUserById(int id) { //use for deleting own account

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
                "id", id);
        String query = "delete from user where id=:id";
        template.update(query, namedParameters);
    }
    
    void updateUser(User user) { //need another one like this to update songs (get user id, send that to song table as Id.

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", user.getUserId());
        paramMap.put("user_name", user.getUserName());
        paramMap.put("email", user.getEmail());
        String query = "update user set user_name=:user_name, email=:email where id=:id ";
        template.update(query, paramMap);
    }
}
