package com.assign.TuneTribe.mod;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.assign.TuneTribe.TopSongs.TopSongsService;
import com.assign.TuneTribe.admin.AdminService;
import com.assign.TuneTribe.post.Post;
import com.assign.TuneTribe.post.PostService;
import com.assign.TuneTribe.song.Song;
import com.assign.TuneTribe.song.SongService;
import com.assign.TuneTribe.user.UserServ;

/**
 *
 * @author Emmanuel
 */

@Controller
@RequestMapping("/mod")
public class ModController {

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
        
           System.out.println(posts.get(1).getUsername());
        model.addAttribute("user", service.getUser(currUser));
        model.addAttribute("postList", posts);
        model.addAttribute("songList", songMap);
        return "mod/mod";
    }

}
