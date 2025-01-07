package gr.dit.voluntia.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // @Builder
@Entity
public class Admin extends User {

    @Column(unique = true, nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String accountKey;     // NOTE:
                                    // In our application the Admin is unique and only one
                                    // This is a special unique-Key, which belongs only to the Admin
                                    // So only Admin can actually access their account knowing this key
                                    // TODO: maybe to add more security here ...

}
