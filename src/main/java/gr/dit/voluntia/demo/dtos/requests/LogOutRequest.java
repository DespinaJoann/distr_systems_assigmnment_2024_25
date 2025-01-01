package gr.dit.voluntia.demo.dtos.requests;

import lombok.Data;

// NOTE: to log out to kanei o user efosson einai logged in
// ara einai methodos tou UserInterface
@Data
public class LogOutRequest {
    // TODO: ...
    private Boolean loggedOut; // true = logout , false = remains logged in ?!
    private Long userId;
}
