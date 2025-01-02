package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.acts.DeleteRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.LogOutRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignInRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignUpRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.DisplayProfileRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.EditProfileInfoRequest;
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

        if (!uniqueAdmin()) {
            // The admin is already logged in or if the table is not empty,
            // then return null (-> the controller will display and error msg)
            return null;
        }
        // Create a new admin
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(request.getPassword());
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setAccountKey(request.getSpecialAdminKey());
        admin.setIsLoggedIn(true);
        // Save the new instance to the Data Base
        // Return the new object or null if something went wrong
        return adminRepository.save(admin);
    }

    @Override
    public User logIn(SignInRequest request) {
        // Attempt to find an admin by the provided credentials (username, password, and account key)
        return adminRepository.findByUsernameAndPasswordAndAccountKey(
                request.getUsername(),
                request.getPassword(),
                request.getSpecialAdminKey()
        );
    }

    @Override
    public User logOut(LogOutRequest request) {
        // Retrieve the admin by ID and log them out by setting isLoggedIn to false
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        if (admin.isPresent()) {
            // The Optional contains a non-null value
            admin.get().setIsLoggedIn(false);
            return admin.get();
        }

        // Return null if no admin is found with the provided ID
        return null;
    }

    @Override
    public User deleteAccount(DeleteRequest request) {
        // Retrieve the admin by ID and validate the provided password and special key
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        if (admin.isPresent()) {
            // The Optional contains a non-null value
            Admin currentAdmin = admin.get();

            // Ensure the provided password and special admin key match before deleting
            if (currentAdmin.getAccountKey().equals(request.getSpecialAdminKey())
            && currentAdmin.getPassword().equals(request.getPassword())) {

                // If validated, delete the admin from the repository and return the deleted admin
                adminRepository.deleteById(currentAdmin.getId());
                return currentAdmin;
            }
        }
        // Return null if no admin is found or the credentials don't match
        return null;
    }

    // Business logic for User actions
    @Override
    public List<String> displayProfileInfo(DisplayProfileRequest request) {
        // Retrieve the admin's profile information and return it as a list of strings
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        return admin.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null); // Return null if no admin is found
    }

    @Override
    public User editProfileInfo(EditProfileInfoRequest request) {
        // Retrieve the admin by ID and update their profile information based on the provided request
        return adminRepository.findById(request.getUserId()).map(admin -> {
            admin.setUsername(request.getUsername() != null ? request.getUsername() : admin.getUsername());
            admin.setPassword(request.getPassword() != null ? request.getPassword() : admin.getPassword());
            admin.setEmail(request.getEmail() != null ? request.getEmail() : admin.getEmail());
            admin.setFirstName(request.getFirstName() != null ? request.getFirstName() : admin.getFirstName());
            admin.setLastName(request.getLastName() != null ? request.getLastName() : admin.getLastName());
            // Save the updated admin profile and return it
            return adminRepository.save(admin);
        }).orElse(null); // Return null if no admin is found

    }


    /**Description:
     * Helper method to check if there is already a logged-in admin or if the database is empty
     * */
    public Boolean uniqueAdmin() {
        // Check if there is already an admin logged in
        Optional<Admin> existingAdmin = Optional.ofNullable(adminRepository.findByIsLoggedInTrue());
        if (existingAdmin.isPresent()) {
            return false; // Return false if there is already a logged-in admin
        }

        // Check if the database is empty (i.e., no admins exist)
        return adminRepository.findAll().isEmpty(); // Return true if the table is empty
    }



}





