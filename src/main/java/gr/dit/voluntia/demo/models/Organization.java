package gr.dit.voluntia.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // @Builder
@Entity
public class Organization extends User {

    private String orgName;
    private String phoneNumber;
    private String address;
    private String location;

    private String profileDescription;
    private String organizationType;

    // Its organization has many events
    @OneToMany(
            mappedBy = "organizationId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Event> listOfCurrentEvents = null;
    private String accountStatus = "pending"; // a=> Pending/ Created/ Rejected


    public void changeAccountStatus(String responseFromAdmin) {
        this.accountStatus = responseFromAdmin;
    }


}
