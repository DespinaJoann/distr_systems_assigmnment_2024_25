package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.auths.LogOutDto;
import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(SignUpDto request);
    User logIn(SignInDto request);
    User logOut(LogOutDto request);
}

