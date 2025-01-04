package gr.dit.voluntia.demo.dtos.auths;

import lombok.Data;

@Data
public class DeleteDto {

    private Long userId;
    private String password; // Password for validation
    private String specialAdminKey;  // Only for the Admin
}
