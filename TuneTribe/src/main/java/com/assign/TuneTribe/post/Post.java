
package com.assign.TuneTribe.post;

import com.assign.TuneTribe.song.Song;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
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
    private long postId;
    
    private String username;
    private String caption;
    private Date postDate;
    private long songId;
    private int likeCount;
    
    //if time allows add comment
    
    
    
}
