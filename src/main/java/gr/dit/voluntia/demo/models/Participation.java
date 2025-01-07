package gr.dit.voluntia.demo.models;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Long volunteerId;           // Foreign Key for Volunteer
    @Column(nullable = false)
    private Long eventId;               // Foreign Key for Event
    @Column(nullable = false)
    private Long organizationId;         // Foreign Key for Organization (the creator of the event)

    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String status = "pending";  // (Pending, Confirmed, CheckedIn, Rejected)

    public void updateStatus(String status) {
        this.status = status;
    }


}



