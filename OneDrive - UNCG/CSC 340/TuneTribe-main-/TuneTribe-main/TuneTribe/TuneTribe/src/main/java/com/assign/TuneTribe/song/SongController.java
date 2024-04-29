
package com.assign.TuneTribe.song;


import com.assign.TuneTribe.user.UserServ;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/song")
public class SongController {

    String currUser = "Mau._.Wee";

    
    @Autowired
    private SongService service;
    
    @Autowired 
    private UserServ uService;
    
    @GetMapping("/all")
    public String getAllSongs(Model model) {
        model.addAttribute("songList", service.getAllSongs());
        return "song/song-list"; //need html
    }
    
    @GetMapping("/newsong")
    public String newSongForm(Model model) {
        return "song/song-create";
    }
    
    @PostMapping("/create") //new user form will take us here
    public String creatSong(Song song) {

        service.saveSong(song);
        return "redirect:/song/all";
    }
    
    @GetMapping("/id={id}")
    public String getSong(@PathVariable long id, Model model) {
        model.addAttribute("song", service.getSong(id));
        return "song/song-info";
    }
    
    @GetMapping("/recommend")
    public String getRecommendation(Model model) {
        model.addAttribute("song", service.getRecommendation());
        return "song/song-recommend"; //need html
    }
    
    @GetMapping("/create")
    public String goToUser(Model model) {
        
        return "redirect:/post/newpost"; //need html
    }
    
    @GetMapping("/search")
    public String searchSong(Model model) {
        
        model.addAttribute("user", uService.getUser(currUser));
        return "song/song-search-createpost"; //need html
    }
    
    @GetMapping("/submitsong")
    public String searchSong(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, Model model ) {
        
       model.addAttribute("song", service.searchSong(name + " " + artist));
       model.addAttribute("user", uService.getUser(currUser));
        return "song/song-search-display"; //need html
    }    
}
