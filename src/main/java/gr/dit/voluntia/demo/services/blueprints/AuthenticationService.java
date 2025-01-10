package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.auths.CurrentUser;
import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.NewUser;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(NewUser request);
    User logIn(SignInDto request);
    User logOut(CurrentUser request);
}

