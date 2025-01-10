package gr.dit.voluntia.demo.dtos.auths;

import gr.dit.voluntia.demo.services.CustomUserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
public class CurrentUser {

    private Long id;
    private String username;
    private String password;
    private String role; // ADMIN, VOLUNTEER, ORGANIZATION


    /**
     * DESCRIPTION:
     * Method for retrieving the current User from the security context */
    public static CurrentUser fromSecurityContext() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(userDetails.getUsername());
        currentUser.setRole(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
        currentUser.setId(userDetails.getId());
        return currentUser;
    }
}
