
package com.TuneTribe.WebApp.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mauricio
 */

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    
    private int userId;
    private String caption;
    private int songId;
    private int likeCount;
    private String postDate;  
    //private String comment;
    
    public Post (String caption, int songId){
        this.caption = caption;
        this.songId = songId;
    }
    
    
}
