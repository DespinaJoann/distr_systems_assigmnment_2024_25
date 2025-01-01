package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.*;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.Participation;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VolunteerService implements UserService, AuthenticationService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Methods for Volunteer Activities
    // -> (for every single volunteer  -- things that can do)
    //////////////////////////////////////////////////////////////////////////////////////////
    /**Description:
     * Applies for participating on an event */
    public Participation applyForEvent(Event event) {
        // TODO: ...
        return null;
    }

    /**Description:
     * Searches and selects the event that they want to participate */
    public List<Participation> searchEventForParticipation( ) {
        // TODO: ...
        return null;
    }


    // Methods for Managing itself (login/signin - delete/alter)
    //////////////////////////////////////////////////////////////////////////////////////////

    public Volunteer signUp(SignUpRequest request) {
        // Create a new Volunteer object and fill it with the data
        Volunteer vol = new Volunteer();
        vol.setUsername(request.getUsername());
        vol.setPassword(request.getPassword());
        vol.setEmail(request.getEmail());
        vol.setFirstName(request.getFirstName());
        vol.setLastName(request.getLastName());
        vol.setPhoneNumber(request.getPhoneNumber());
        vol.setDateOfBirth(request.getDateOfBirth());

        // Save the new volunteer to the Data Base
        return volunteerRepository.save(vol);
    }

    @Override
    public User logIn(SignInRequest request) {
        return volunteerRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );
    }

    @Override
    public User logOut(LogOutRequest request) {
        Optional<Volunteer> vol = volunteerRepository.findById(request.getUserId());
        if (vol.isPresent()) {
            // The Optional contains a non-null value
            vol.get().setIsLoggedIn(false);
            return vol.get();
        }

        return null;
    }


    @Override
    public List<String> displayProfileInfo(DisplayProfileRequest request) {
        Optional<Volunteer> vol = volunteerRepository.findById(request.getUserId());
        return vol.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null);
    }

    @Override
    public User editProfileInfo(EditProfileInfoRequest request) {
        return volunteerRepository.findById(request.getUserId()).map(vol -> {
            vol.setUsername(request.getUsername() != null ? request.getUsername() : vol.getUsername());
            vol.setPassword(request.getPassword() != null ? request.getPassword() : vol.getPassword());
            vol.setEmail(request.getEmail() != null ? request.getEmail() : vol.getEmail());
            vol.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : vol.getPhoneNumber());
            vol.setFirstName(request.getFirstName() != null ? request.getFirstName() : vol.getFirstName());
            vol.setLastName(request.getLastName() != null ? request.getLastName() : vol.getLastName());

            return volunteerRepository.save(vol);
        }).orElse(null);

    }

    @Override
    public User deleteAccount(DeleteRequest request) {
        Optional<Volunteer> vol = volunteerRepository.findById(request.getUserId());
        if (vol.isPresent()) {
            // The Optional contains a non-null value
            Volunteer currentVol = vol.get();

            // Validate password and special key before deleting for security purposes
            if (currentVol.getPassword().equals(request.getPassword())) {

                // Delete the admin from the repository
                volunteerRepository.deleteById(currentVol.getId());
                return currentVol;
            }
        }
        return null;
    }
}
