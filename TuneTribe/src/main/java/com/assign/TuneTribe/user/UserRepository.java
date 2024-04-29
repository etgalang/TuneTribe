
package com.assign.TuneTribe.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shauna
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUserName(String username);
   
    @Query("SELECT COUNT(u) FROM User u WHERE u.role NOT IN ('Admin')")
long countByRoleNotAdmin();

 }
