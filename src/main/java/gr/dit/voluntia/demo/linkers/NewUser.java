package gr.dit.voluntia.demo.linkers;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewUser {
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
    private String orgName = " ";
    private String address = " ";
    private String location = " ";
    private String orgType = " ";


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
            ", organizationName='" + orgName + '\'' +
            ", address='" + address + '\'' +
            ", location='" + location + '\'' +
            ", organizationType='" + orgType + '\'' +
            '}';
    }

}
