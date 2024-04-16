
package com.TuneTribe.WebApp.user;

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
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    private String userName;
    private String name;
    private String email;
    private String password;
    
    /*
    * All the following are 
    */
    private int following;
    private int follower;
    private int post;
    private int topSong;
    
    //Issue, was thinking about storing an arrayList of follower ids but how would that be stored in the db?
    //Will need to somehow access post db using a post id
    //Same for top songs, access 3 different songs from songs db using the 3 stored id nums
    
    //Constructor for creating new User
    public User (String userName, String email, String password, String name){ 
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    //Do i need a contructor for when stuff is imported?
    
    
    
}
