package gr.dit.voluntia.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
// This table will inherit the User's (superclass) attributers
@EqualsAndHashCode(callSuper = true)
public class Volunteer extends User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;

    private Boolean hasCheckedIn = false;
    private String accountStatus = "Pending"; // => Pending/ Created/ Rejected

    // Its volunteer could have many participations
    @OneToMany(
            mappedBy = "volunteerId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Participation> listOfParticipation = null;

    // Update Methods
    /**Description: Add new participation */
    public void addNewParticipation(Participation participation) {
        if (this.listOfParticipation == null) {
            this.listOfParticipation = new ArrayList<>();
        }
        this.listOfParticipation.add(participation);
    }
    /**Description: Remove a participation */
    public void deleteParticipation(Participation participation) {
        if (this.listOfParticipation != null) {
            this.listOfParticipation.remove(participation);
        }
    }

    /**Description: Change some of the information of the profile */
    public void editAccountProperties(String responseFromAdmin) {
        // TODO: ...
        //  Maybe it will not be a String prop but a List<String> or a JSON format

    }


}



