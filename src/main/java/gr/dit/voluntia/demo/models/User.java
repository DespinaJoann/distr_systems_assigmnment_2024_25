package gr.dit.voluntia.demo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

// This class will be a mapped superclass entity, meaning it will
// be a base entity, which will pass all its attributes to its
// subclasses (all the other models), but it will not be an actual
// table to the Data Base

// This class will be a mapped superclass entity;
// Meaning it will be a base entity, which will inherits all its attributes
// to its subclasses (all the other models), but it will not be an actual table
// to the Data Base

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    private Boolean isLoggedIn;
}
