package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.auths.LogOutDto;
import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.UserForm;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(UserForm request);
    User logIn(SignInDto request);
    User logOut(LogOutDto request);
}

