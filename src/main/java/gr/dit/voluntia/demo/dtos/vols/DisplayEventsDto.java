package gr.dit.voluntia.demo.dtos.vols;

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
public class DisplayEventsDto {
    // Nothing to update
    private Boolean nothingToUpdate = true;
        // if nothingToUpdate is false, then the list updated
        // otherwise there is nothing for this search

    // Input fields -> Filters
    private String status;      // f.ex: 'confirmed'
    private String date ;       // f.ex: '2024-01-10'
    private String topic;       // f.ex: 'Environment'
    private String location;    // f.ex: 'Athens Greece'

    // Returned fields
    private List<Event> filteredEvents = new ArrayList<>();


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
