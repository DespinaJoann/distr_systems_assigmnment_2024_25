package gr.dit.voluntia.demo.services.blueprints;


import gr.dit.voluntia.demo.dtos.requests.LogOutRequest;
import gr.dit.voluntia.demo.dtos.requests.SignInRequest;
import gr.dit.voluntia.demo.dtos.requests.SignUpRequest;
import gr.dit.voluntia.demo.models.User;

public interface AuthenticationService {
    User signUp(SignUpRequest request);
    User logIn(SignInRequest request);
    User logOut(LogOutRequest request);
}

