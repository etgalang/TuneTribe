
package com.assign.TuneTribe.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Mauricio
 */

@Service
public class UserServ {
    
    @Autowired
    UserRepo repo;
    
    /**
     * Get all users
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    
    /**
     * Find one user by ID.
     * @param id
     * @return the user
     */
    public User getUser(String username) {
        return repo.getUserByUsername(username);
    }
    
     /**
     * Update existing user.
     * @param user 
     */
    public void updateUser(User user) {
        //repo.updateUser(user);
    }
}
