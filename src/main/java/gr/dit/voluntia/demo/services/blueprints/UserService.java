package gr.dit.voluntia.demo.services.blueprints;

import gr.dit.voluntia.demo.dtos.auths.DeleteDto;
import gr.dit.voluntia.demo.dtos.glob.DisplayProfileDto;
import gr.dit.voluntia.demo.dtos.glob.EditProfileInfoDto;
import gr.dit.voluntia.demo.models.User;

import java.util.List;

public interface UserService {

    List<String> displayProfileInfo(DisplayProfileDto request);
    User editProfileInfo(EditProfileInfoDto request);
    User deleteAccount(DeleteDto request);

}

