package gr.dit.voluntia.demo.dtos.admins;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ConfirmUserDto {
    // Nothing to update
    private Boolean nothingToUpdate = true;

    // Fill with counter's values
    private int acceptedVols = 0;
    private int rejectedVols = 0;
    private int acceptedOrgs = 0;
    private int rejectedOrgs = 0;
    // Fill from lists
    private List<Organization> updatedOrganizations= new ArrayList<>();
    private List<Volunteer> updatedVolunteers = new ArrayList<>();

    /**
     * Description:
     * Custom string representation for debugging and logging purposes.
     * Displays the current state of the ConfirmUserDto object.
     */
    @Override
    public String toString() {
        return "ConfirmUserDto {" +
                "nothingToUpdate=" + nothingToUpdate + ", " +
                "acceptedVols=" + acceptedVols + ", " +
                "rejectedVols=" + rejectedVols + ", " +
                "acceptedOrgs=" + acceptedOrgs + ", " +
                "rejectedOrgs=" + rejectedOrgs + ", " +
                "updatedOrganizations=" + updatedOrganizations + ", " +
                "updatedVolunteers=" + updatedVolunteers + '}';
    }


    /**
     * Description:
     * Converts the ConfirmUserDto object into a JSON representation.
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
