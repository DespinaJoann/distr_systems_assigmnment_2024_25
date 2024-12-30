package gr.dit.voluntia.demo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Organization extends User {

    private String orgName;
    private String phoneNumber;
    private String address;
    private String location;


    private List<Event> listOfCurrentEvents = null;
    private String accountStatus = "Pending"; // => Pending/ Created/ Rejected

    /**
     * Description:
     * Creates a new event for the organization.
     *
     * @param eventName the name of the event to be created.
     * @param eventDate the date that will take part.
     * @param location the location of the event.
     * @param maxNumberOfParticipants the maximum number of volunteers
     * @param eventDescription a description of the event.
     * */
    public Event createEvent(
            String eventName,
            LocalDateTime eventDate,
            String location,
            Integer maxNumberOfParticipants,
            String eventDescription
    ) {
        // TODO: ...
        return new Event();
    }

    /**
     * Description:
     * Reviews a volunteer's request to participate in an event.
     *
     * @param volunteer the volunteer object representing the volunteer requesting participation.
     * @param event the event object the volunteer wants to participate in.
     * */
    public Participation reviewVolunteerParticipation(Volunteer volunteer, Event event ) {
        // TODO: ...
        // if the Event is well suited for the Volunteer

        return null;
    }

    /**
     * Description:
     * Confirms a volunteer's participation in a specific event.
     * It is called after the reviewVolunteerParticipation method
     *
     * @param volunteer the volunteer object representing the volunteer requesting participation.
     * @param participation the participation object that keeps all the information.
     * */
    public void confirmVolunteerParticipation(Volunteer volunteer, Participation participation ) {
        // TODO: ...
    }

    /**
     * Description:
     * Confirms a volunteer's participation in a specific event.
     *
     * @param responseFromAdmin the admin's response indicating the new account status
     * ("Created" or "Rejected").
     **/
    public void changeAccountStatus(String responseFromAdmin) {
        this.accountStatus = responseFromAdmin;
    }


}
