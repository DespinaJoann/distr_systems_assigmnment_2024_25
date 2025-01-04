package gr.dit.voluntia.demo.dtos.org;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.dit.voluntia.demo.models.Event;
import lombok.Data;

@Data
public class CreateNewEventDto {

    // Nothing to update
    private Boolean nothingToUpdate = true;

    // The id of the Organization that makes the request to
    // create a new Event
    private Long organizationId;
    private String name;
    private String description;
    private String location;
    private String Date;
    private String topic;
    private Integer maxNumberOfPeople;

    // The new Event that will be created
    // The returned value
    private Event event;

     /**
     * Description:
     * Custom string representation for debugging and logging purposes.
     * Displays the current state of the ConfirmEventsDto object.
     */
    @Override
    public String toString() {
        // make it appropriate
        // TODO: ...
        return "";
    }


    /**
     * Description:
     * Converts the Dto's classe object into a JSON representation.
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
