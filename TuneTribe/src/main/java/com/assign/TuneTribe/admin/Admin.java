package com.assign.TuneTribe.admin;

<<<<<<< HEAD
=======

import com.assign.TuneTribe.user.User;
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
<<<<<<< HEAD
 *
=======
 *user table has role id, username, password and can login in depending on role type. if the role is Admin then admin can access another table called admin . moderator can use the the admin to do its own functions
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
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
    private String adminUserName;
    private String adminEmail;
    private String adminPassword;
    private String role;
    private String communityGuidelines;
     private String copyright;
=======
 
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
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
<<<<<<< HEAD
     
     
=======
>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298

    public String getCommunityGuidelines() {
        return communityGuidelines;
    }

    public void setCommunityGuidelines(String communityGuidelines) {
        this.communityGuidelines = communityGuidelines;
    }
<<<<<<< HEAD
    
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
=======

>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

<<<<<<< HEAD
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

  

}
=======
    

}

>>>>>>> fbe07ab22d812ffa7f86708cc768a82fe22b6298
