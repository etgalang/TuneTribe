package com.assign.TuneTribe.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author shauna
 */
@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> getRegularUsers() {
        List<User> allUsers = repo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .filter(user -> !user.getRole().equals("Artist"))
                .collect(Collectors.toList());
    }

    public List<User> getArtistUsers() {
        List<User> allUsers = repo.findAll();
        return allUsers.stream()
                .filter(user -> !user.getRole().equals("Admin"))
                 .filter(user -> !user.getRole().equals("User"))
                .collect(Collectors.toList());
    }

    public User getUser(long id) {
        return repo.getReferenceById(id);
    }

    public void deleteUser(long id) {
        repo.deleteById(id);
    }

    public User getUserByUserName(String userName) {
        return repo.findByUserName(userName).orElseThrow(()
                -> new UsernameNotFoundException(userName + "not found"));
    }

    public void toggleUserBan(Long userId) {
        Optional<User> userOptional = repo.findById(userId);
        userOptional.ifPresent(user -> {
            user.setBanned(!user.isBanned());
            repo.save(user);
        });
    }

    public boolean isUserBanned(String username) {
        Optional<User> userOptional = repo.findByUserName(username);
        return userOptional.map(User::isBanned).orElse(false);
    }
}
