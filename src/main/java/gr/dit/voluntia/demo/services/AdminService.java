package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.SignInRequest;
import gr.dit.voluntia.demo.dtos.SignUpRequest;
import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(SignUpRequest request) {
        // Create a new admin
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(request.getPassword());
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setAccountKey(request.getSpecialAdminKey());

        // Save the new instance to the Data Base and return the new object
        return adminRepository.save(admin);
    }

    public Admin findAdmin(SignInRequest request) {
        Admin admin = adminRepository.findByUsernameAndPasswordAndAccountKey(
                request.getUsername(),
                request.getPassword(),
                request.getSpecialAdminKey()
        );

        if (admin == null) {
            throw new IllegalArgumentException("Invalid Admin credentials or account key.");
        }
        return admin;
    }
}
