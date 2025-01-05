package gr.dit.voluntia.demo.dtos.glob;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileInfoDto {

    private Long userId;
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
}
