
package com.assign.TuneTribe.post;

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
@RequestMapping("/post")
public class PostController {
    
    @Autowired
    private PostService service;
    
    @GetMapping("/all") //convert this to get all post for a user?
    public String getAllPosts (Model model){
        model.addAttribute("postList",service.getAllPosts());
        return "post/post-list";
    }
    
    @GetMapping("/newpost")
    public String newPostForm(Model model) {
        return "post/post-create";
    }
    
    @PostMapping("/create") //new user form will take us here
    public String creatPost(Post post) {

        service.savePost(post);
        return "redirect:/post/all"; //redirect to home page?
    }
    
    @GetMapping("/id={id}")
    public String getPost(@PathVariable long id, Model model) {
        model.addAttribute("post", service.getPost(id));
        return "post/post-viewPost";
    }
    
}
