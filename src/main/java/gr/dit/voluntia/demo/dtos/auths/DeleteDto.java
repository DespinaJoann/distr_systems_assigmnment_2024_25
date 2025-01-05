package gr.dit.voluntia.demo.dtos.auths;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteDto {

    private Long userId;
    private String password; // Password for validation
    private String specialAdminKey;  // Only for the Admin
}
