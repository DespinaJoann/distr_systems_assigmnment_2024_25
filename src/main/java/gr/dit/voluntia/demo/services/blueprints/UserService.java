package gr.dit.voluntia.demo.services.blueprints;

import gr.dit.voluntia.demo.dtos.requests.acts.DeleteRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.DisplayProfileRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.EditProfileInfoRequest;
import gr.dit.voluntia.demo.models.User;

import java.util.List;

public interface UserService {

    List<String> displayProfileInfo(DisplayProfileRequest request);
    User editProfileInfo(EditProfileInfoRequest request);
    User deleteAccount(DeleteRequest request);

}

