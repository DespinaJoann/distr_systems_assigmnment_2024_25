package gr.dit.voluntia.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // @Builder
@Entity
public class Volunteer extends User {

    @Column(unique = true, nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String dateOfBirth;         // String -> "yyyy-MM-dd"

    private String profileDescription;

    private Boolean hasCheckedIn = false;

    @Column(nullable = false)
    private String accountStatus = "pending"; // => Pending/ Created/ Rejected


    @Override
    public void setRole(String role) {
        super.setRole("VOLUNTEER");
    }

    // Its volunteer could have many participations
    @OneToMany(
            mappedBy = "volunteerId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Participation> listOfParticipation;

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

    /**
     * Description:
     * Calculates the age of the volunteer based on the dateOfBirth (which is stored as a String).
     * @return The age of the volunteer.
     */
    public int calculateAge() {
        // Initialize the formater to our selected pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convert String to LocalDate
        LocalDate birthDate = LocalDate.parse(this.dateOfBirth, formatter);
        // Get current date
        LocalDate currentDate = LocalDate.now();
        // Calculate the period between the birthDate and current date
        Period period = Period.between(birthDate, currentDate);
        // Return the number of years (age)
        return period.getYears();
    }

}



