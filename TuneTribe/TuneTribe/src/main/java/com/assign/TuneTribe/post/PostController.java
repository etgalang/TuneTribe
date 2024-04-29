
package com.assign.TuneTribe.post;

import com.assign.TuneTribe.song.Song;
import com.assign.TuneTribe.song.SongService;
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
 * @author Mauricio
 */

@Controller
@RequestMapping("/post")
public class PostController {
    
    String currUser = "Mau._.Wee";
    
    @Autowired
    private PostService service;
    
    @Autowired
    private SongService sService;
    
    @GetMapping("/all") //convert this to get all post for a user?
    public String getAllPosts (Model model){
        model.addAttribute("postList",service.getAllPosts());
        return "post/post-list";
    }
    
    @GetMapping("/newpost")
    public String newPostForm(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, Model model) {
        
        model.addAttribute("song", sService.searchSong(name + " " + artist));
        
        return "post/post-create";
    }
    
    @GetMapping("/create") //new user form will take us here
    public String createPost(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, 
            @RequestParam("post-caption")String caption) {
        Song tempSong = new Song();
        tempSong = sService.searchSong(name + " " + artist);
        sService.saveSong(tempSong);
        tempSong = sService.getSongByName(tempSong.getName());
        
        service.savePost(tempSong.getId(), caption, currUser);
        return "redirect:/user/home";
    }
    
    @GetMapping("/id={id}")
    public String getPost(@PathVariable long id, Model model) {
        model.addAttribute("post", service.getPost(id));
        return "post/post-viewPost";
    }
    
}
