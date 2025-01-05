package gr.dit.voluntia.demo.dtos.auths;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private String role;

    // Special attributes - specific for each different role
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;

    private String profileDescription;
    private String organizationName;
    private String address;
    private String location;
    private String organizationType;

    private String specialAdminKey;     // Only for the Admin
}
