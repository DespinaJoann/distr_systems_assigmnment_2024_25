package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.repositories.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    public List<Event> getAllRejectedEventsWithOrganizationId(Long orgId) {
        List<Event> evList = eventRepository.findEventsByOrgIdAndStatus("rejected", orgId);
        return evList == null ? new ArrayList<Event>() : evList;
    }


    public List<Event> getAllExpiredEventsWithOrganizationId(Long orgId) {
        List<Event> evList = eventRepository.findEventsByOrgIdAndStatus("expired", orgId);
        return evList == null ? new ArrayList<Event>() : evList;
    }

    /**
     * Description:
     * Finds all expired events for a specific organization.
     *
     * @param orgId the organization ID
     * @return List of expired events
     * * */
    @Transactional
    public List<Event> calculateExpiredEvents(Long orgId) {
        // Fetch all events for the organization
        List<Event> events = eventRepository.findByOrganizationId(orgId);

        // Get today's date
        LocalDate now = LocalDate.now();

        // Define the date format (assuming the format is always YYYY-MM-DD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // List to hold expired events
        List<Event> expiredEvents = new ArrayList<>();

        // Loop through the events and add expired ones to the list
        for (Event event : events) {
            // Assuming Event has a 'date' field (String in YYYY-MM-DD format)
            String eventDateStr = event.getDate();

            // Parse the event's date from the string
            LocalDate eventDate = LocalDate.parse(eventDateStr, formatter);

            // If the event date has passed, add it to the expired list
            if (eventDate.isBefore(now)) {
                event.setStatus("expired");  // Mark the event as expired
                eventRepository.save(event);  // Save the event with "expired" status
                expiredEvents.add(event);     // Add to the expired events list
            }
        }

        return expiredEvents;
    }

    public Boolean eventExistsByName(String name) {
        return eventRepository.existsByName(name);
    }
}
