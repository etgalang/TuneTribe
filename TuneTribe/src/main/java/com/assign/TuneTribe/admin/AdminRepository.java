/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assign.TuneTribe.admin;

<<<<<<< HEAD

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.TuneTribe.user.User;

=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
/**
 *
 * @author shauna
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
<<<<<<< HEAD
    Optional<Admin> findByUser(User adminUser);
=======
    
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
}
