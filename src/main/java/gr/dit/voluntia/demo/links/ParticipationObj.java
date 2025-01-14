package gr.dit.voluntia.demo.links;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationObj {
    private Long partId;
    private Event event;
    private Volunteer vol;
    private Long orgId;
    private Organization org;
    private String status;

}
