package gr.dit.voluntia.demo.dtos.requests.tasks;

import lombok.Data;

@Data
public class CreateNewEventRequest {

    // The id of the Organization that makes the request to
    // create a new Event
    public Long organizationId;

    public String name;
    public String description;
    public String location;
    public String Date;
    public Integer maxNumberOfPeople;

}
