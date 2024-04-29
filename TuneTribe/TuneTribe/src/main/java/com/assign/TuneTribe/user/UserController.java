
package com.assign.TuneTribe.user;

import com.assign.TuneTribe.post.Post;
import com.assign.TuneTribe.post.PostService;
import com.assign.TuneTribe.song.Song;
import com.assign.TuneTribe.song.SongService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public static String currUser="Mau._.Wee";
    
    @Autowired 
    private UserServ service;
    
    @Autowired
    private PostService pService;
    
    @Autowired
    private SongService sService;
    
    @GetMapping({"/", "","/home"}) //we need user info and post info here
    public String homePage (Model model) {
        
        List<Post> posts = pService.getAllPosts();
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
    public String myProfile (Model model){
        model.addAttribute("user", service.getUser(currUser));
        return "user/user-myprofile";
    }
    
    
    //@GetMapping("/myProfile={username}")
    //public String myProfile(@PathVariable String username,Model model){
        /*
        if(currUser.isEmpty()){
            currUser = username;
        }
        else {
            username = currUser;
        }*/
       // model.addAttribute("user", service.getUser(username));
       //return "user/user-myprofile";
    //}

    
    //this is for friends, not working yet   
    @GetMapping("/username={username}")
    public String getUser(@PathVariable String username, Model model) {
        model.addAttribute("user", service.getUser(username));
        return "user/user-myprofile";
    }
    
}
