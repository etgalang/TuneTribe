package com.assign.TuneTribe.admin;

import com.assign.TuneTribe.mod.Mod;
import com.assign.TuneTribe.mod.ModRepository;
import com.assign.TuneTribe.report.Report;
import com.assign.TuneTribe.report.ReportRepository;
import com.assign.TuneTribe.user.User;
import com.assign.TuneTribe.user.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author shauna
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    ModRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ReportRepository reportRepo;

    public User getUser(long id) {
        return userRepo.getReferenceById(id);
    }

    public List<User> getUsers() {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .filter(user -> !user.getRole().equals("Mod"))
                .filter(user -> !user.getRole().equals("Artist"))
                .collect(Collectors.toList());
    }

    public List<User> getArtists() {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .filter(user -> !user.getRole().equals("Mod"))
                .filter(user -> !user.getRole().equals("User"))
                .collect(Collectors.toList());
    }

    public List<User> getMods() {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .filter(user -> !user.getRole().equals("Artist"))
                .filter(user -> !user.getRole().equals("User"))
                .collect(Collectors.toList());
    }

   // public List<Report> getReports() {
    //    List<Report> allReports = reportRepo.findAll();
   // }

    public User getUserByUserName(String userName) {
        return userRepo.findByUserName(userName).orElseThrow(()
                -> new UsernameNotFoundException(userName + "not found"));
    }

    public void toggleUserBan(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        userOptional.ifPresent(user -> {
            user.setBanned(!user.isBanned());
            userRepo.save(user);
        });
    }

 
    public void banReportedUser(Long userId) {
        Optional<User> reportedUserOptional = userRepo.findById(userId);
        reportedUserOptional.ifPresent(reportedUser -> {
            // Check if the reported user is not already banned
            if (!reportedUser.isBanned()) {
                // Ban the reported user
                reportedUser.setBanned(true);
                userRepo.save(reportedUser);
            }
        });
    }

    // Unban the reported user
    public void unbanReportedUser(Long userId) {
        Optional<User> reportedUserOptional = userRepo.findById(userId);
        reportedUserOptional.ifPresent(reportedUser -> {
            // Check if the reported user is currently banned
            if (reportedUser.isBanned()) {
                // Unban the reported user
                reportedUser.setBanned(false);
                userRepo.save(reportedUser);
            }
        });
    }


    public boolean isUserBanned(String username) {
        Optional<User> userOptional = userRepo.findByUserName(username);
        return userOptional.map(User::isBanned).orElse(false);
    }


    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }


public String getCommunityGuidelines(Principal principal) {
    String role = getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        Optional<User> adminUserOptional = userRepo.findByUserName(principal.getName());
        return adminUserOptional.flatMap(adminUser ->
                adminRepository.findByUser(adminUser).map(Admin::getCommunityGuidelines)
        ).orElse("");
    } else {
        // For non-admin users, simply return the copyright text without checking for authorization
        Optional
<String> guidelinesOptional = adminRepository.findById(1L).map(Admin::getCommunityGuidelines);
return guidelinesOptional.orElse("");
}
}

public void saveCommunityGuidelines(String guidelinesText, Principal principal) {
    String role = getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        Optional<User> adminUserOptional = userRepo.findByUserName(principal.getName());
        adminUserOptional.ifPresent(adminUser -> {
            Admin admin = adminRepository.findByUser(adminUser).orElse(new Admin());
            admin.setUser(adminUser);
            admin.setCommunityGuidelines(guidelinesText);
            adminRepository.save(admin);
        });
    } else {
        // Handle unauthorized access
    }
}

public String getTermsOfService(Principal principal) {
    String role = getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        Optional<User> adminUserOptional = userRepo.findByUserName(principal.getName());
        return adminUserOptional.flatMap(adminUser ->
                adminRepository.findByUser(adminUser).map(Admin::getTermsOfService)
        ).orElse("");
    } else {
        // For non-admin users, simply return the copyright text without checking for authorization
        Optional
<String> termsOptional = adminRepository.findById(1L).map(Admin::getTermsOfService);
return termsOptional.orElse("");
}
}

public void saveTermsOfService(String termsText, Principal principal) {
    String role = getUserRole(principal.getName());
    if (role != null && role.equals("Admin")) {
        Optional<User> adminUserOptional = userRepo.findByUserName(principal.getName());
        adminUserOptional.ifPresent(adminUser -> {
            Admin admin = adminRepository.findByUser(adminUser).orElse(new Admin());
            admin.setUser(adminUser);
            admin.setTermsOfService(termsText);
            adminRepository.save(admin);
        });
    } else {
        // Handle unauthorized access
    }
}

// Utility method to fetch user role
public String getUserRole(String username) {
    Optional<User> userOptional = userRepo.findByUserName(username);
    return userOptional.map(User::getRole).orElse(null);
}



public long getTotalUsers() {
    return userRepo.countByRoleNotAdmin();
}

    
    public User getByUsername(String username) {
        Optional<User> userOptional = userRepo.findByUserName(username);
        return userOptional.orElse(null); // Return user if present, otherwise null
    }
}
