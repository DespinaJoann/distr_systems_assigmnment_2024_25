package gr.dit.voluntia.demo.dtos.auths;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignInDto {

    private String signAs;          // = the Role
                                    // Determine a specific role of the sign in
                                    // login as: (Admin, User, Organization)
    private String username;
    private String password;


}