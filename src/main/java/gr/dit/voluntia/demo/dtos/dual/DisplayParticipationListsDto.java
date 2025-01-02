package gr.dit.voluntia.demo.dtos.dual;


import gr.dit.voluntia.demo.models.Event;
import lombok.Data;

import java.util.List;

@Data
public class DisplayParticipationListsDto {

    private Long organizationId;
    private String collectionNameFromStatus;        // List collection based on the participation status

    private String infoMessage;
    private Boolean taskCompleted = false;
}
