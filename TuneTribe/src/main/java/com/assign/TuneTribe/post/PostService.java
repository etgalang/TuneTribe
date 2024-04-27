
package com.assign.TuneTribe.post;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mauricio
 */

@Service
public class PostService {
    
    @Autowired
    PostRepository repo;
    
    /**
     * Get all posts
     * @return the list of posts.
     */
    public List<Post> getAllPosts() {
        return repo.findAll();
    }
    
    /**
     * Find one post by ID.
     * @param postId
     * @return the post
     */
    public Post getPost(long id){
        return repo.getPostById(id);
    }
    
    /**
     * Delete post by ID.
     * @param postId 
     */
    public void deletePost (long id){
        repo.deleteByPostId(id);
    }
    
    /**
     * Save post entry. 
     * @param user 
     */
    public void savePost(Post post) {
        repo.savePost(post);
    }
    
}
