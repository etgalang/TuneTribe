package com.assign.TuneTribe.admin;

<<<<<<< HEAD

import com.assign.TuneTribe.user.User;
=======
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
=======
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
<<<<<<< HEAD
 *user table has role id, username, password and can login in depending on role type. if the role is Admin then admin can access another table called admin . moderator can use the the admin to do its own functions
=======
 *
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
 * @author shauna
 */
@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
<<<<<<< HEAD
 
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    private String communityGuidelines;
    private String copyright;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
=======
    private String adminUserName;
    private String adminEmail;
    private String adminPassword;
    private String role;
    private String communityGuidelines;
     private String copyright;
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
<<<<<<< HEAD
=======
     
     
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c

    public String getCommunityGuidelines() {
        return communityGuidelines;
    }

    public void setCommunityGuidelines(String communityGuidelines) {
        this.communityGuidelines = communityGuidelines;
    }
<<<<<<< HEAD

=======
    
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

<<<<<<< HEAD
    
=======
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

  
>>>>>>> 28836a7e4e028a2a96636e27126d413f5c78541c

}
