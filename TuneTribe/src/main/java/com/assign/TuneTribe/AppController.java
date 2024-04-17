
package com.assign.TuneTribe;

import com.assign.TuneTribe.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author shauna
 */
@Controller
public class AppController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
