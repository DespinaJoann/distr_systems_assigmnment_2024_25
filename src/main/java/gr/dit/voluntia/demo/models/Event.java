package gr.dit.voluntia.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String date;
    private int maxNumbOfVolunteers;

    private String status = "Pending";  // (Pending, Rejected, Approved)

    public String getEventDetails() {
        // TODO:...
        // return a JSON formated of the toString method
        return null;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

}
