package com.assign.TuneTribe.admin;

import com.assign.TuneTribe.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author shauna
 */
@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
 
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

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
        

    public String getCommunityGuidelines() {
        return communityGuidelines;
    }

    public void setCommunityGuidelines(String communityGuidelines) {
        this.communityGuidelines = communityGuidelines;
    }

      
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

}
