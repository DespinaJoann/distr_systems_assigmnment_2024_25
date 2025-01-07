package gr.dit.voluntia.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

    @Column(unique = true, nullable = false)
    private String orgName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    private String location;

    @Column(nullable = false)
    private String profileDescription;
    
    @Column(nullable = false)
    private String organizationType;

    // Its organization has many events
    @OneToMany(
            mappedBy = "organizationId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Event> listOfCurrentEvents = null;
    @Column(nullable = false)
    private String accountStatus = "pending"; // a=> Pending/ Created/ Rejected


    public void changeAccountStatus(String responseFromAdmin) {
        this.accountStatus = responseFromAdmin;
    }


}
