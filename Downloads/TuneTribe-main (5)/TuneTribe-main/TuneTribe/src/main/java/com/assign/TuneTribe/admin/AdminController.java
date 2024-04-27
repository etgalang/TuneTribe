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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private ModService modService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/user")
    public String getUsers(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getUsers());
        return "admin/list-users";
    }

    @GetMapping("/artist")
    public String getArtists(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getArtists());
        return "admin/list-artists";
    }

    @GetMapping("/tunetribe")
    public String login() {
        return "admin/tunetribe";
    }

    @GetMapping("/user/{userID}")
    public String deleteUser(@PathVariable Long userID) {
        adminService.deleteUser(userID);
        return "redirect:/admin/user";
    }

    @PostMapping("/user/{userId}/toggle-ban")
    public String toggleUserBan(@PathVariable Long userId) {
        adminService.toggleUserBan(userId);
        return "redirect:/admin/user"; // Redirect back to the user list page
    }

    @GetMapping("/artist/{userID}")
    public String deleteArtist(@PathVariable Long userID) {
        adminService.deleteUser(userID);
        return "redirect:/admin/artist";
    }

    @PostMapping("/artist/{userId}/toggle-ban")
    public String toggleArtistBan(@PathVariable Long userId) {
        adminService.toggleUserBan(userId);
        return "redirect:/admin/artist"; // Redirect back to the user list page
    }

    @GetMapping("/moderator")
    public String getMods(Model model, @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("userList", adminService.getMods());
        return "admin/list-mod";
    }

    @GetMapping("/moderator/{modId}")
    public String deleteMod(@PathVariable Long modId) {
        modService.deleteMod(modId);
        return "redirect:/admin/moderator";
    }

    @GetMapping("/modReport")
    public String viewRequests(Model model) {
        List<Mod> modReport = adminService.getAllRequests(); // Assuming you have a service for managing mod requests
    model.addAttribute("modReport", modReport);
   
        return "admin/modReport";
    }

    @GetMapping("/updates")
    public String getUpdatesForm(Model model) {
        return "admin/updates";
    }
/* 
    @GetMapping("/community-guidelines")
    public String getCommunityGuidelinesForm(Model model) {
        String guidelines = adminService.getCommunityGuidelines();
        model.addAttribute("guidelines", guidelines);
        return "admin/community-guidelines-form";
    }

    @PostMapping("/save-community-guidelines")
    public String saveCommunityGuidelines(@RequestParam("guidelines") String guidelinesText) {
        adminService.saveCommunityGuidelines(guidelinesText);
        return "redirect:/admin/community-guidelines";
    }

    @GetMapping("/tunetribe-guidelines")
    public String getTuneTribeGuidelines(Model model) {
        String guidelines = adminService.getCommunityGuidelines();
        model.addAttribute("guidelines", guidelines);
        return "admin/tunetribe-guidelines";
    }
*/


@GetMapping("/community-guidelines")
public String getCommunityGuidelinesForm(Model model, Principal principal) {
    String guidelines = adminService.getCommunityGuidelines(principal);
    model.addAttribute("guidelines", guidelines);
    return "admin/community-guidelines-form";
}

@PostMapping("/save-community-guidelines")
public String saveCommunityGuidelines(@RequestParam("guidelines") String guidelinesText, Principal principal) {
    adminService.saveCommunityGuidelines(guidelinesText, principal);
    return "redirect:/admin/community-guidelines";
}

@GetMapping("/tunetribe-guidelines")
public String getTuneTribeGuidelines(Model model, Principal principal) {
    String role = adminService.getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        String guidelines = adminService.getCommunityGuidelines(principal);
        model.addAttribute("guidelines", guidelines);
        return "admin/tunetribe-guidelines";
    } else {
        // Redirect or handle unauthorized access
        return "redirect:/error";
    }
}

@GetMapping("/copyright-rules")
public String getCopyRightForm(Model model, Principal principal) {
    String copyright = adminService.getCopyRight(principal);
    model.addAttribute("copyright", copyright);
    return "admin/copyright-form";
}

@PostMapping("/save-copyright-rules")
public String saveCopyRight(@RequestParam("copyright") String copyrightText, Principal principal) {
    adminService.saveCopyRight(copyrightText, principal);
    return "redirect:/admin/copyright-rules";
}

@GetMapping("/tunetribe-copyright")
public String getTuneTribeCopyright(Model model, Principal principal) {
    String role = adminService.getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        String copyright = adminService.getCopyRight(principal);
        model.addAttribute("copyright", copyright);
        return "admin/tunetribe-copyright";
    } else {
        // Redirect or handle unauthorized access
        return "redirect:/error";
    }
}


 /*    @GetMapping("/copyright-rules")
    public String getCopyRightForm(Model model) {
        String copyright = adminService.getCopyRight();
        model.addAttribute("copyright", copyright);
        return "admin/copyright-form";
    }

    @PostMapping("/save-copyright-rules")
    public String saveCopyRight(@RequestParam("copyright") String copyrightText) {
        adminService.saveCopyRight(copyrightText);
        return "redirect:/admin/copyright-rules";
    } */

  /*  @GetMapping("/tunetribe-copyright")
    public String getTuneTribeCopyright(Model model) {
        String copyright = adminService.getCopyRight();
        model.addAttribute("copyright", copyright);
        return "admin/tunetribe-copyright";
    } */

    @GetMapping("/user/id={id}")
    public String getUserProfile(@PathVariable long id, Model model) {
        model.addAttribute("user", adminService.getUser(id));
        return "admin/user-profile";
    }

   
}

