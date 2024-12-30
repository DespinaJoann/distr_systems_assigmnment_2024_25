package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.SignInRequest;
import gr.dit.voluntia.demo.dtos.SignUpRequest;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer createVolunteer(SignUpRequest request) {
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

    public Volunteer findVolunteer(SignInRequest request) {
        Volunteer vol = volunteerRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (vol == null) {
            throw new IllegalArgumentException("Invalid Volunteer credentials.");
        }
        return vol;
    }

}
