package com.assign.TuneTribe.controller;

import com.assign.TuneTribe.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shaun
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void saveCommunityGuidelines(String guidelinesText) {
        Admin guidelines = new Admin();
        guidelines.setText(guidelinesText);
        adminRepository.save(guidelines);
    }

}
