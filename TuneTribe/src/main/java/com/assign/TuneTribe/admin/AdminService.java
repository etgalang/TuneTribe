package com.assign.TuneTribe.admin;

import com.assign.TuneTribe.mod.Mod;
import com.assign.TuneTribe.mod.ModRepository;
import com.assign.TuneTribe.user.User;
import com.assign.TuneTribe.user.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author shaun
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
        @Autowired
    ModRepository repo;
        
            @Autowired
    UserRepository userRepo;

    public void saveCommunityGuidelines(String guidelinesText) {
        Admin guidelines = new Admin();
        guidelines.setText(guidelinesText);
        adminRepository.save(guidelines);
    }
    
    public List<User> getUsers() {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
               .collect(Collectors.toList());
    }

 

    public User getUser(long id) {
        return userRepo.getReferenceById(id);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

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

    public boolean isUserBanned(String username) {
        Optional<User> userOptional = userRepo.findByUserName(username);
        return userOptional.map(User::isBanned).orElse(false);
    }

}
