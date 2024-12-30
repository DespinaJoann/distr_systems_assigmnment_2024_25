package gr.dit.voluntia.demo.dtos;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String role;

    // Special attributes - specific for each different role
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private String organizationName;
    private String address;
    private String location;

    private String specialAdminKey;     // Only for the Admin
}
