package gr.dit.voluntia.demo.dtos.requests.acts;

import lombok.Data;

@Data
public class DeleteRequest {

    private Long userId;
    private String password; // Password for validation
    private String specialAdminKey;  // Only for the Admin
}
