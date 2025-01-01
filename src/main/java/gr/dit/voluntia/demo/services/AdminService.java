package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.*;
import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// NOTE: In this class in each method, when the result is null means failure
// The failure or the success response will be voiced from the Controller

@Service
public class AdminService implements UserService, AuthenticationService {

    @Autowired
    private AdminRepository adminRepository;

    // Methods for managing other Users
    //////////////////////////////////////////////////////////////////////////////////////////


    /**Description:
     * Approve new user and activate their profile*/
    public Boolean approveUser(User user) {
        // TODO: ...
        return false;
    }

    /**Description:
     * Approve new event and change its status*/
    public Boolean approveEvent(Event event) {
        // TODO: ...
        return false;
    }

    // Methods for Managing itself (login/signin - delete/alter)
    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public User signUp(SignUpRequest request) {
        // Create a new admin
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(request.getPassword());
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setAccountKey(request.getSpecialAdminKey());

        // Save the new instance to the Data Base
        // Return the new object or null if something went wrong
        return adminRepository.save(admin);
    }

    @Override
    public User logIn(SignInRequest request) {

        return adminRepository.findByUsernameAndPasswordAndAccountKey(
                request.getUsername(),
                request.getPassword(),
                request.getSpecialAdminKey()
        );
    }

    @Override
    public User logOut(LogOutRequest request) {
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        if (admin.isPresent()) {
            // The Optional contains a non-null value
            admin.get().setIsLoggedIn(false);
            return admin.get();
        }

        return null;
    }

    @Override
    public User deleteAccount(DeleteRequest request) {
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        if (admin.isPresent()) {
            // The Optional contains a non-null value
            Admin currentAdmin = admin.get();

            // Validate password and special key before deleting for security purposes
            if (currentAdmin.getAccountKey().equals(request.getSpecialAdminKey())
            && currentAdmin.getPassword().equals(request.getPassword())) {

                // Delete the admin from the repository
                adminRepository.deleteById(currentAdmin.getId());
                return currentAdmin;
            }

            // If admin does not exist, return null
        }

        return null;
    }

    // Business logic for User actions
    @Override
    public List<String> displayProfileInfo(DisplayProfileRequest request) {
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        return admin.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null);
    }

    @Override
    public User editProfileInfo(EditProfileInfoRequest request) {
        return adminRepository.findById(request.getUserId()).map(admin -> {
            admin.setUsername(request.getUsername() != null ? request.getUsername() : admin.getUsername());
            admin.setPassword(request.getPassword() != null ? request.getPassword() : admin.getPassword());
            admin.setEmail(request.getEmail() != null ? request.getEmail() : admin.getEmail());
            admin.setFirstName(request.getFirstName() != null ? request.getFirstName() : admin.getFirstName());
            admin.setLastName(request.getLastName() != null ? request.getLastName() : admin.getLastName());

            return adminRepository.save(admin);
        }).orElse(null);

    }
}





