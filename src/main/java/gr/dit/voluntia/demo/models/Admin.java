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

    @Override
    public void setRole(String role) {
        super.setRole("ADMIN");
    }
}
