/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assign.TuneTribe.controller;

import com.assign.TuneTribe.mod.ModRepository;
import com.assign.TuneTribe.mod.ModService;
import com.assign.TuneTribe.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author shauna
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private ModService modService;
    
      @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/home"})
    public String dashboard(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "index";
    }

    @GetMapping("/user")
    public String getUsers(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", service.getRegularUsers());
        return "admin/list-users";
    }
    

    // handler method to handle delete student request
    @GetMapping("/user/{userID}")
    public String deleteUser(@PathVariable Long userID) {
        service.deleteUser(userID);
        return "redirect:/admin/user";
    }


    @PostMapping("/user/{userId}/toggle-ban")
    public String toggleUserBan(@PathVariable Long userId) {
        service.toggleUserBan(userId);
        return "redirect:/admin/user"; // Redirect back to the user list page
    }

    @GetMapping("/moderator")
    public String getMods(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("modList", modService.getMods());
        return "admin/list-mod";
    }

    @GetMapping("/moderator/{modId}")
    public String deleteMod(@PathVariable Long modId) {
        modService.deleteMod(modId);
        return "redirect:/admin/moderator";
    }    

    @GetMapping("/modRequests")
    public String viewRequests(Model model) {
        return "admin/modRequests";
    }
    
     @GetMapping("/updates")
    public String getUpdatesForm(Model model) {
        return "admin/updates";
    }
    

        @PostMapping("/save-guidelines")
    public String saveCommunityGuidelines(@RequestParam("guidelines") String guidelinesText) {
        adminService.saveCommunityGuidelines(guidelinesText);
        return "redirect:/admin/home";
    }


}
