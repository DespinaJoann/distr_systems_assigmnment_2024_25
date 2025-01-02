package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.requests.acts.LogOutRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignInRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignUpRequest;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(SignUpRequest request);
    User logIn(SignInRequest request);
    User logOut(LogOutRequest request);
}

