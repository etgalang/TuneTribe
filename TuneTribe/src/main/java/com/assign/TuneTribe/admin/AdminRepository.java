<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assign.TuneTribe.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

=======
package com.assign.TuneTribe.admin;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.TuneTribe.user.User;

>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
/**
 *
 * @author shauna
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
<<<<<<< HEAD
    
=======
    Optional<Admin> findByUser(User adminUser);
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
}
