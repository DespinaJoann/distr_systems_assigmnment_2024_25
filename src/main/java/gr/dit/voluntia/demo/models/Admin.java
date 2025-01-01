package gr.dit.voluntia.demo.models;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Admin extends User {

    private String firstName;
    private String lastName;

    private String accountKey;     // NOTE:
                                    // In our application the Admin is unique and only one
                                    // This is a special unique-Key, which belongs only to the Admin
                                    // So only Admin can actually access their account knowing this key
                                    // TODO: maybe to add more security here ...

}
