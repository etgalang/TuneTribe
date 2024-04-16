
package com.TuneTribe.WebApp.user;

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
    private UserService service;
    
    @GetMapping("/id={id}")
    public String myProfile (@PathVariable int id, Model model) {
        model.addAttribute ("user", service.getUser(id));
        return "user/user-profile";
    }   
    
   
}
