package gr.dit.voluntia.demo.dtos.org;

import gr.dit.voluntia.demo.models.Event;
import lombok.Data;

@Data
public class CreateNewEventDto {

    // The id of the Organization that makes the request to
    // create a new Event
    public Long organizationId;

    public String name;
    public String description;
    public String location;
    public String Date;
    public Integer maxNumberOfPeople;

    // The new Event that will be created
    public Event event;
}
