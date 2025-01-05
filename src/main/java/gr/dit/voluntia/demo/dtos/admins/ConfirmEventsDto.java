package gr.dit.voluntia.demo.dtos.admins;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.dit.voluntia.demo.models.Event;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConfirmEventsDto {
    // Nothing to update
    private Boolean nothingToUpdate = true;

    // Fill with counter's values
    private int acceptedEvents = 0;
    private int rejectedEvents = 0;

    // Fill from lists
    private List<Event> updatedEvents = new ArrayList<>();

    /**
     * Description:
     * Custom string representation for debugging and logging purposes.
     * Displays the current state of the ConfirmEventsDto object.
     */
    @Override
    public String toString() {
        // make it appropriate
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
