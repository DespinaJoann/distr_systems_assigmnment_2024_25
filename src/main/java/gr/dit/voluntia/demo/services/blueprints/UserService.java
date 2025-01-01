package gr.dit.voluntia.demo.services.blueprints;

import gr.dit.voluntia.demo.dtos.requests.DeleteRequest;
import gr.dit.voluntia.demo.dtos.requests.DisplayProfileRequest;
import gr.dit.voluntia.demo.dtos.requests.EditProfileInfoRequest;
import gr.dit.voluntia.demo.models.User;

import java.util.List;

public interface UserService {

    List<String> displayProfileInfo(DisplayProfileRequest request);
    User editProfileInfo(EditProfileInfoRequest request);
    User deleteAccount(DeleteRequest request);

}
