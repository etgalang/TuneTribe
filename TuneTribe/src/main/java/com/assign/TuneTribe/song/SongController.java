
package com.assign.TuneTribe.song;

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
@RequestMapping("/song")
public class SongController {
    
    @Autowired
    private SongService service;
    
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
    
    @GetMapping("/goback")
    public String goToUser(Model model) {
        
        return "redirect:/user/all"; //need html
    }
    
    
}
