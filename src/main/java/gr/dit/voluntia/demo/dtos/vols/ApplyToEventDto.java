package gr.dit.voluntia.demo.dtos.vols;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.dit.voluntia.demo.models.Participation;
import lombok.Data;

@Data
public class ApplyToEventDto {

    // Input values from the form
    private String eventName;
    private String volunteerId;         // This will be taken from the system because
                                        // user (volunteer) is logged in to the web app
                                        // How can I do it actually that?
                                        // It is quite interesting though!
                                        //  ^
                                        //  |

                    // VITAL: Maybe to use a session service for that and generally
                    // for storing the Id and the info from the logged in user, while
                    // they are on their browser.
                    // To do it I probably must create another class that works on that
                    // Maybe todo (SessionService)

    // Searched values
    private String eventId;             // Search by event name on table='Event' -> get eventId
    private String organizationId;      //  and also from this search -> get organizationId


    // Created value
    private Participation newParticipation;

    /**
     * Description:
     * Custom string representation for debugging and logging purposes.
     * Displays the current state of the ConfirmEventsDto object.
     */
    @Override
    public String toString() {
        return "ApplyToEventDto{" +
                "volunteerId='" + volunteerId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                '}';
    }


    /**
     * Description:
     * Converts the Dto's class object into a JSON representation.
     * @return A JSON String containing the object's data
     */
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return "{}";// Returns an empty JSON in case of error
        }
    }
}


