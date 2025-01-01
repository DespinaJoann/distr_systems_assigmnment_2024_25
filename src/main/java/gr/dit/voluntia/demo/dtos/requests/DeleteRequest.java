package gr.dit.voluntia.demo.dtos.requests;

import lombok.Data;

@Data
public class DeleteRequest {

    private Long userId;
    private String password; // Password for validation
    private String specialAdminKey;  // Only for the Admin
}
