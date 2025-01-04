package gr.dit.voluntia.demo.dtos.org;


import lombok.Data;

@Data
public class DisplayParticipationListsDto {

    private Long organizationId;
    private String collectionNameFromStatus;        // List collection based on the participation status

    private String infoMessage;
    private Boolean taskCompleted = false;
}
