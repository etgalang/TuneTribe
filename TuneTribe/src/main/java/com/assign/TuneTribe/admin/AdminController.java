/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assign.TuneTribe.admin;

import com.assign.TuneTribe.mod.Mod;
import com.assign.TuneTribe.mod.ModService;
import com.assign.TuneTribe.user.User;
import com.assign.TuneTribe.user.UserRepository;
import com.assign.TuneTribe.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author shauna
 */
@Controller
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private ModService modService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private UserRepository userRepo;

 @GetMapping("/admin/user")
    public String getUsers(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getUsers());
        return "admin/list-users";
    }

    @GetMapping("/admin/artist")
    public String getArtists(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getArtists());
        return "admin/list-artists";
    }

    @GetMapping("/user/user-home")
    public String toTuneTribeHome() {
        return "user/user-home";
    }

    @GetMapping("/admin/user/{userID}")
    public String deleteUser(@PathVariable Long userID) {
        adminService.deleteUser(userID);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/{userId}/toggle-ban")
    public String toggleUserBan(@PathVariable Long userId) {
        adminService.toggleUserBan(userId);
        return "redirect:/admin/user"; // Redirect back to the user list page
    }

    @GetMapping("/admin/artist/{userID}")
    public String deleteArtist(@PathVariable Long userID) {
        adminService.deleteUser(userID);
        return "redirect:/admin/artist";
    }

    @PostMapping("/admin/artist/{userId}/toggle-ban")
    public String toggleArtistBan(@PathVariable Long userId) {
        adminService.toggleUserBan(userId);
        return "redirect:/admin/artist"; // Redirect back to the user list page
    }

    @GetMapping("/admin/moderator")
    public String getMods(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getMods());
        return "admin/list-mod";
    }

    @GetMapping("/admin/moderator/{userID}")
    public String deleteMod(@PathVariable Long userID) {
        adminService.deleteUser(userID);
        return "redirect:/admin/moderator";
    }

   

    @GetMapping("/admin/modRequests")
    public String viewRequests(Model model) {
        List<Mod> modRequest = adminService.getAllRequests(); // Assuming you have a service for managing mod requests
    model.addAttribute("modRequest", modRequest);
   
        return "admin/modRequests";

    
 }

    @GetMapping("/admin/updates")
    public String getUpdatesForm(Model model) {
        return "admin/updates";
    }
 


@GetMapping("/admin/community-guidelines")
public String getCommunityGuidelinesForm(Model model, Principal principal) {
    String guidelines = adminService.getCommunityGuidelines(principal);
    model.addAttribute("guidelines", guidelines);
    return "admin/community-guidelines-form";
}

@PostMapping("/admin/save-community-guidelines")
public String saveCommunityGuidelines(@RequestParam("guidelines") String guidelinesText, Principal principal) {
    adminService.saveCommunityGuidelines(guidelinesText, principal);
    return "redirect:/admin/community-guidelines";
}


@GetMapping("/tunetribe-guidelines")
public String getTuneTribeGuidelines(Model model, Principal principal) {
    String role = adminService.getUserRole(principal.getName());
    String guidelines;
    if (role != null) {
        // Assuming adminService.getCopyRight(principal) returns the copyright text
        guidelines = adminService.getCommunityGuidelines(principal);
    } else {
        // Handle unauthorized access
        return "redirect:/error";
    }
    // Print the copyright value for debugging
    System.out.println("Guideline text: " + guidelines);
    model.addAttribute("guidelines", guidelines);
    return "tunetribe-guidelines";
}



@GetMapping("/admin/terms-service")
public String getTermsOfServiceForm(Model model, Principal principal) {
    String termsService = adminService.getTermsOfService(principal);
    model.addAttribute("termsService", termsService);
    return "admin/terms-serviceform"; // Issue: returning the wrong view
}

@PostMapping("/admin/save-terms-service")
public String saveTermsOfService(@RequestParam("termsService") String termsText, Principal principal) {
    adminService.saveTermsOfService(termsText, principal);
    return "redirect:/admin/terms-service";
}


@GetMapping("/tunetribe-service")
public String getTuneTribeService(Model model, Principal principal) {
    String role = adminService.getUserRole(principal.getName());
    String termsService;
    if (role != null) {
        termsService = adminService.getTermsOfService(principal);
    } else {
        return "redirect:/error";
    }
    model.addAttribute("termsService", termsService);
    return "tunetribe-service"; // Issue: returning the wrong view
}


    @GetMapping("/user/user-myprofile/id={id}")
    public String getUserProfile(@PathVariable long id, Model model) {
        model.addAttribute("user", adminService.getUser(id));
        return "user/user-myprofile";
    }

}

