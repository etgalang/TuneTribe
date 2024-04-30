package com.assign.TuneTribe;

import com.assign.TuneTribe.TopSongs.TopSongs;
import com.assign.TuneTribe.TopSongs.TopSongsService;
import com.assign.TuneTribe.admin.AdminService;
import com.assign.TuneTribe.mod.ModService;
import com.assign.TuneTribe.user.User;
import com.assign.TuneTribe.user.UserRepository;
import com.assign.TuneTribe.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author shauna
 */
@Controller
public class AppController {

    @Autowired
    UserRepository repo;

    @Autowired
    UserService service;

    @Autowired
    TopSongsService tpService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModService modService;

    @GetMapping(value = {"", "/"})
    public String dashboard(Model model) {
        // Check if the user is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Check if the user has the admin role
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
                long totalUsers = adminService.getTotalUsers();
                model.addAttribute("totalUsers", totalUsers);

                return "admin/admin"; // Redirect admin users to admin page
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Artist"))) {

                return "redirect:/artist/home";
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Mod"))) {

                return "redirect:/mod/mod";
            } else {
                // Regular users go to root page
                String name = auth.getName();
                model.addAttribute("currentUser", name); //returns username
                System.out.println("Details " + auth.getDetails());//
                System.out.println("Authorities /'" + auth.getAuthorities().toString() + "/'");//
                System.out.println("Authorities /'" + auth.getAuthorities() + "/'");//
                return "redirect:/user/home";
            }
        }
        return "redirect:/login"; // Redirect unauthenticated users to login page
    }

    @GetMapping(value = {"/admin/home"})
    public String admin(Model model) {
        long totalUsers = adminService.getTotalUsers();
        model.addAttribute("totalUsers", totalUsers);

        return "admin/admin";
    }

    @GetMapping("/mod/mod")
    public String mod(Model model) {
        return "mod/mod";
    } 

    @GetMapping("/artist/home")
    public String artist(Model model) {
        return "artist/home";
    } 

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        service.saveUser(user);
        tpService.saveTopSongs(new TopSongs(user.getId()));
        return "redirect:/login";
    }

}
