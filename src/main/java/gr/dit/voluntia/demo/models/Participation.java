package gr.dit.voluntia.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key for Volunteer
    private Long volunteerId;
    // Foreign Key for Event
    private Long eventId;

    private LocalDateTime date;
    private String status = "Pending";  // (Pending, Confirmed, CheckedIn, Rejected)

    public void updateStatus(String status) {
        this.status = status;
    }
}



