package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.auths.DeleteDto;
import gr.dit.voluntia.demo.dtos.auths.LogOutDto;
import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.dtos.glob.DisplayProfileDto;
import gr.dit.voluntia.demo.dtos.glob.EditProfileInfoDto;
import gr.dit.voluntia.demo.dtos.vols.ApplyToEventDto;
import gr.dit.voluntia.demo.dtos.vols.DisplayEventsDto;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class VolunteerService implements UserService, AuthenticationService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Methods for Volunteer Activities
    // -> (for every single volunteer  -- things that can do)
    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Description:
     * Searches for events based on filters in the DTO (status, date, topic, location).
     * If no filters are provided, retrieves all events with status = "Confirmed".
     *
     * @param dispEvdto The DTO containing input filters (status, date, topic, location).
     * @return The DTO populated with filtered events or empty if no matches.
     * */
    public DisplayEventsDto searchForEvent(DisplayEventsDto dispEvdto) {
        // Extract filters from the input dto
        String status = dispEvdto.getStatus();
        String date = dispEvdto.getDate();
        String topic = dispEvdto.getTopic();
        String location = dispEvdto.getLocation();

        // Retrieve filtered events based on provided filters
        List<Event> filteredEvents;

        if (status == null && date == null && topic == null && location == null) {
            // No filters provided: default to events with status = "Confirmed"
            filteredEvents = eventRepository.findEventsByStatus("Confirmed");
        } else {
            // Filters are provided: retrieve based on all filters
            filteredEvents = eventRepository.findEventsByFilters(status, date, topic, location);
        }

        // Check if any events match the filters
        if (filteredEvents.isEmpty()) {
            dispEvdto.setFilteredEvents(Collections.emptyList());
            dispEvdto.setNothingToUpdate(true);
            return dispEvdto;
        }

        // Store all the info to the dto
        dispEvdto.setFilteredEvents(filteredEvents);
        dispEvdto.setNothingToUpdate(false);

        return dispEvdto;
    }


    /**Description:
     *  */
    public ApplyToEventDto applyToEvent(ApplyToEventDto appTodto ) {
        // Extract the data info from the dto
        Long volId = Long.valueOf(appTodto.getVolunteerId());
        String evName = appTodto.getEventName();

        // Search the event based on its unique name
        Event ev = eventRepository.findByName(evName);
        if (ev == null) {
            appTodto.setVolunteerId(null);
            return appTodto;
        }

        // Extract all the attributes from the event
        Long evId = ev.getId();
        Long orgId = ev.getOrganizationId();

        // Create a new participation
        Participation newPart  = new Participation();
        newPart.setVolunteerId(volId);
        newPart.setEventId(evId);
        newPart.setOrganizationId(orgId);
        newPart.setStatus("pending");


        // Save the new participation to the repository
        participationRepository.save(newPart);

        // Update the dto and return
        appTodto.setNewParticipation(newPart);
        appTodto.setBugged(false);
        return appTodto;
    }


    // Methods for Managing itself (login/signin - delete/alter)
    //////////////////////////////////////////////////////////////////////////////////////////

    public Volunteer signUp(SignUpDto request) {
        // Ensure password is hashed before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new Volunteer object and fill it with the data
        Volunteer vol = new Volunteer();
        vol.setUsername(request.getUsername());
        vol.setPassword(hashedPassword);
        vol.setEmail(request.getEmail());
        vol.setFirstName(request.getFirstName());
        vol.setLastName(request.getLastName());
        vol.setPhoneNumber(request.getPhoneNumber());
        vol.setDateOfBirth(request.getDateOfBirth());
        vol.setProfileDescription(request.getProfileDescription());
        vol.setIsLoggedIn(false);
        // Save the new volunteer to the Data Base
        return volunteerRepository.save(vol);
    }

    public Volunteer logIn(String username) {
        Volunteer vol = volunteerRepository.findByUsername(username);
        if (vol != null) {
            vol.setIsLoggedIn(true);
            return vol; // Return admin object
        }
        return null; // No matching admin found
    }

    @Override
    public User logIn(SignInDto request) {
        // Same login logic using password encoding verification
        Volunteer vol = volunteerRepository.findByUsername(request.getUsername());
        if (vol != null && passwordEncoder.matches(request.getPassword(), vol.getPassword())) {
            vol.setIsLoggedIn(true);
            return vol;
        }
        return null; // Invalid credentials
    }

    @Override
    public User logOut(LogOutDto request) {
        Optional<Volunteer> vol = volunteerRepository.findById(request.getUserId());
        if (vol.isPresent()) {
            // The Optional contains a non-null value
            vol.get().setIsLoggedIn(false);
            return vol.get();
        }

        return null;
    }


    @Override
    public List<String> displayProfileInfo(DisplayProfileDto request) {
        Optional<Volunteer> vol = volunteerRepository.findById(request.getUserId());
        return vol.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null);
    }

    @Override
    public User editProfileInfo(EditProfileInfoDto request) {
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
    public User deleteAccount(DeleteDto request) {
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



