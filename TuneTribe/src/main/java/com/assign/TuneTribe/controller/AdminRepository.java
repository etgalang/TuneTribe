/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assign.TuneTribe.controller;

import com.assign.TuneTribe.mod.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shaun
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
