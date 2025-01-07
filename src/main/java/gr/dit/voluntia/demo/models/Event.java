package gr.dit.voluntia.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizationId;

    @Column(nullable = false)
    private String name;
    private String description;
    private String location;
    private String topic;

    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private int maxNumbOfVolunteers;

    @Column(nullable = false)
    private String status = "pending";  // (Pending, Rejected, Approved)

    // Each event could have many participations
    @OneToMany(
            mappedBy = "eventId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Participation> participationList;

    public String getEventDetails() {
        // TODO:...
        // return a JSON formated of the toString method
        return null;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

}



