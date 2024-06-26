
package com.assign.TuneTribe.user;

import com.assign.TuneTribe.TopSongs.TopSongsService;
import com.assign.TuneTribe.post.Post;
import com.assign.TuneTribe.post.PostService;
import com.assign.TuneTribe.song.Song;
import com.assign.TuneTribe.song.SongService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
   
    public static String currUser="";
    
    @Autowired 
    private UserServ service;
    
    @Autowired
    private PostService pService;
    
    @Autowired
    private SongService sService;
    
    @Autowired
    private TopSongsService tpService;
    
    @GetMapping({"/", "","/home"}) //we need user info and post info here
    public String homePage (@CurrentSecurityContext(expression="authentication?.name") String username, Model model) {
        currUser = username;
        List<Post> posts = pService.getAllPosts();
        Collections.reverse(posts);
        List<Song> songs = sService.getAllSongs();
        
        //Next Create a map to associate a songId with a Song object
        Map<Long, Song> songMap = new HashMap<>();
        for (Song song: songs) {
            songMap.put(song.getId(),song);
        }
        
        model.addAttribute("user", service.getUser(currUser));
        model.addAttribute("postList", posts);
        model.addAttribute("songMap", songMap);
        return "user/user-home";
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
    
    
    @GetMapping ("/myProfile")
    public String myProfile (@CurrentSecurityContext(expression="authentication?.name") String username, Model model){
        currUser = username;
        List<Song> songs = sService.getAllSongs();
        model.addAttribute("user", service.getUser(currUser));
        model.addAttribute("songs", songs);
        model.addAttribute("topSongs", tpService.findByTopSongsById
        (service.getUser(currUser).getId()));
        
        return "user/user-myprofile";
    }
    
    
    @GetMapping("/myProfile=")
    public String myProfileTwo(@CurrentSecurityContext(expression="authentication?.name") String username,Model model){
        currUser = username;
     List<Song> songs = sService.getAllSongs();
        model.addAttribute("user", service.getUser(currUser));
        model.addAttribute("songs", songs);
        model.addAttribute("topSongs", tpService.findByTopSongsById
        (service.getUser(currUser).getId()));
        
        return "user/user-myprofile";
    }
    
    @GetMapping("/updateTopSong={songnum}")
    public String myProfile(@PathVariable int songnum, Model model){
        
        List<Song> songs = sService.getAllSongs();

        model.addAttribute("user", currUser);
        model.addAttribute("songs", songs);
        model.addAttribute("topSong", tpService.findByTopSongsById
        (service.getUser(currUser).getId()) );
        return"redirect:/song/search={songnum}";
    }
    
    
    
    //this is for friends, not working yet   
    @GetMapping("/username={username}")
    public String getUser(@PathVariable String username, Model model) {
        model.addAttribute("user", service.getUser(username));
        return "user/user-myprofile";
    }
    
}
