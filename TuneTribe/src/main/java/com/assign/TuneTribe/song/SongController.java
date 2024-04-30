
package com.assign.TuneTribe.song;


import com.assign.TuneTribe.TopSongs.TopSongsService;
import static com.assign.TuneTribe.user.UserController.currUser;
import com.assign.TuneTribe.user.UserServ;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mauricio
 */

@Controller
@RequestMapping("/song")
public class SongController {

    String currUser = "";
    int songNum;

    @Autowired
    private TopSongsService tpService;
    
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
    public String newSongForm(@CurrentSecurityContext(expression="authentication?.name")
            String username, Model model) {
        currUser = username;
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
    public String getRecommendation(@CurrentSecurityContext(expression="authentication?.name")
            String username, Model model) {
        currUser = username;
        model.addAttribute("song", service.getRecommendation());
        return "song/song-recommend"; //need html
    }
    
    @GetMapping("/create")
    public String goToUser(Model model) {
        
        return "redirect:/post/newpost"; //need html
    }
    
    @GetMapping("/search")
    public String searchSong(@CurrentSecurityContext(expression="authentication?.name") String username, Model model) {
        currUser = username;
        model.addAttribute("user", uService.getUser(currUser));
        return "song/song-search-createpost"; //need html
    }
    
    
    @GetMapping("/search={songnum}")
    public String addSongSearch(@CurrentSecurityContext(expression="authentication?.name") String username, @PathVariable int songnum, Model model) {
        List<Song> songs = service.getAllSongs();
        songNum = songnum;
        currUser = username;
        model.addAttribute("songs", songs);
        model.addAttribute("user", uService.getUser(currUser));
        model.addAttribute("replace", songNum);
        
        return "song/song-search-addsong"; //need html
    }
    
    @GetMapping("/submitaddsong")
    public String addSongSearchSong(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, @RequestParam("replacing-name")String oldName,
            @RequestParam("replacing-artist")String oldArtist,@RequestParam("songNum")int songNum, Model model ) {
        
        
        Song song = service.searchSong(name + " " + artist);

        service.saveSong(song);
        song = service.getSongByName(song.getName());
        System.out.println(song.getId());
        System.out.println(song.getName());
        //long id, int selection, long songId
        tpService.updateTopSong(uService.getUser(currUser).getId(), songNum,
                song.getId());
        
        
       //model.addAttribute("song", service.searchSong(name + " " + artist));
       //model.addAttribute("user", uService.getUser(currUser));
        return "redirect:/user/myProfile"; //need html
    }    
    
    @GetMapping("/submitsong")
    public String searchSong(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, Model model ) {
        
       model.addAttribute("song", service.searchSong(name + " " + artist));
       model.addAttribute("user", uService.getUser(currUser));
        return "song/song-search-display"; //need html
    }    
    
    @GetMapping("/addSongSubmit")
    public String addSongSearchSubmit(@RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, Model model ) {
        
       model.addAttribute("song", service.searchSong(name + " " + artist));
       model.addAttribute("user", uService.getUser(currUser));
        return "song/song-search-display"; //need html
    }   
}
