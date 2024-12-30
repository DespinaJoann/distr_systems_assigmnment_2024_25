package gr.dit.voluntia.demo.dtos;

import lombok.Data;

@Data
public class SignInRequest {

    private String signAs;          // Determine a specific role of the sign in
                                    // login as: (Admin, User, Organization)
    private String username;
    private String password;

    private String specialAdminKey;  // Only for the Admin


}