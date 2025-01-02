package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.forward.LogOutDto;
import gr.dit.voluntia.demo.dtos.forward.SignInDto;
import gr.dit.voluntia.demo.dtos.forward.SignUpDto;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(SignUpDto request);
    User logIn(SignInDto request);
    User logOut(LogOutDto request);
}

