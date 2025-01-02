package gr.dit.voluntia.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Organization extends User {

    private String orgName;
    private String phoneNumber;
    private String address;
    private String location;

    // Its organization has many events
    @OneToMany(
            mappedBy = "organization_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Event> listOfCurrentEvents = null;
    private String accountStatus = "Pending"; // a=> Pending/ Created/ Rejected


    public void changeAccountStatus(String responseFromAdmin) {
        this.accountStatus = responseFromAdmin;
    }


}
