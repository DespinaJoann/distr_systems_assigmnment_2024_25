package gr.dit.voluntia.demo.dtos.dual;

import lombok.Data;

@Data
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
