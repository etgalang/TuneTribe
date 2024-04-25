
package com.assign.TuneTribe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mauricio
 */

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserServ service;
    
    @GetMapping({"/", "","/home"}) //we need user info and post info here
    public String homePage (Model model) {
        //model.addAttribute("user", service.getAllUsers());
        return "/user";
    }
  
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", service.getAllUsers());
        return "user/user-list";
    }
    
    @GetMapping("/newaccount")
    public String newUserForm(Model model) {
        return "user/user-createaccount";
    }
    
    //this is for friends, not working yet
    
    @GetMapping("/username={username}")
    public String getUser(@PathVariable String username, Model model) {
        model.addAttribute("user", service.getUser(username));
        return "user/user-myprofile";
    }
    
}
