package gr.dit.voluntia.demo.dtos;

import lombok.Data;

@Data
public class DeleteRequest {
    private String username;
    private String password;

    private String specialAdminKey;  // Only for the Admin
}
