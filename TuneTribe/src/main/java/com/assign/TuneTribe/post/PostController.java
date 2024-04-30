
package com.assign.TuneTribe.post;

import com.assign.TuneTribe.report.Report;
import com.assign.TuneTribe.report.ReportService;
import com.assign.TuneTribe.song.Song;
import com.assign.TuneTribe.song.SongService;
import com.assign.TuneTribe.user.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
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
    
    String currUser = " ";
    
    @Autowired
    private PostService service;
    
    @Autowired
    private SongService sService;
    
    @Autowired
    private ReportService rService;
    
    @Autowired
    private UserServ uService;
    
    @GetMapping("/all") //convert this to get all post for a user?
    public String getAllPosts (Model model){
        model.addAttribute("postList",service.getAllPosts());
        return "post/post-list";
    }
    
    @GetMapping("/newpost")
    public String newPostForm(@CurrentSecurityContext(expression="authentication?.name") String username, 
            @RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, Model model) {
        currUser = username;
        model.addAttribute("song", sService.searchSong(name + " " + artist));
        
        return "post/post-create";
    }
    
    @GetMapping("/create") //new user form will take us here
    public String createPost(@CurrentSecurityContext(expression="authentication?.name") String username, 
            @RequestParam("song-name") String name,
            @RequestParam("song-artist")String artist, 
            @RequestParam("post-caption")String caption) {
        currUser = username;
        Song tempSong = new Song();
        tempSong = sService.searchSong(name + " " + artist);
        sService.saveSong(tempSong);
        tempSong = sService.getSongByName(tempSong.getName());
        
        service.savePost(tempSong.getId(), caption, currUser);
        return "redirect:/user/home";
    }
    
    
    @GetMapping("/id={id}")
    public String getPost(@CurrentSecurityContext(expression="authentication?.name") String username, 
            @PathVariable long id, Model model) {
        currUser = username;
        model.addAttribute("post", service.getPost(id));
        
        return "post/post-viewPost";
    }
    
    /*@GetMapping("/report")
    public String reportPost (){
        
        return "mod/report";
    }*/
    @GetMapping("/report={postId}")
    public String reportPost (@CurrentSecurityContext(expression="authentication?.name") String username,
            @PathVariable long postId, Model model){
        currUser = username;
        model.addAttribute("reporter", uService.getUser(currUser));
        model.addAttribute("reported", uService.getUser(service.getPost(postId).getUsername()));
        model.addAttribute("post", service.getPost(postId));
         
        return "mod/report";
    }
    
    @GetMapping("/createreport")
    public String reportPost (@CurrentSecurityContext(expression="authentication?.name") String username,
            @RequestParam("postId") long postId,@RequestParam("userName") String reporter,
            @RequestParam("reportedUser")String reported, 
            @RequestParam("explanation")String explanation, Model model){
        
        currUser = username;
        Report report = new Report();
        report.setPostId(postId);
        report.setReportedId(uService.getUser(reported).getId());
        report.setReporterId(uService.getUser(reporter).getId());
        report.setExplanation(explanation);
        
        rService.saveReport(report);
         
        return "redirect:/user/home";
    }
    
}
