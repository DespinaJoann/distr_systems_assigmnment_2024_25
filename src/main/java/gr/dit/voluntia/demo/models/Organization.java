package gr.dit.voluntia.demo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Organization extends User {

    private String orgName;
    private String phoneNumber;
    private String address;
    private String location;


    private List<Event> listOfCurrentEvents = null;
    private String accountStatus = "Pending"; // => Pending/ Created/ Rejected


    public void changeAccountStatus(String responseFromAdmin) {
        this.accountStatus = responseFromAdmin;
    }


}
