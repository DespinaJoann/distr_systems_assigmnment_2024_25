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
    private String firstName = " ";
    private String lastName  = " ";
    private String phoneNumber = " ";
    private String dateOfBirth = " ";

    private String profileDescription = " ";
    private String organizationName = " ";
    private String address = " ";
    private String location = " ";
    private String organizationType = " ";

    private String specialAdminKey = " ";     // Only for the Admin

    @Override
    public String toString() {
        return "SignUpDto{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", profileDescription='" + profileDescription + '\'' +
            ", organizationName='" + organizationName + '\'' +
            ", address='" + address + '\'' +
            ", location='" + location + '\'' +
            ", organizationType='" + organizationType + '\'' +
            ", specialAdminKey='" + specialAdminKey + '\'' +
            '}';
    }

}
