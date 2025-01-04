package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>,
        JpaSpecificationExecutor<Event> {

    List<Event> findAllByName(String name);
    List<Event> findAllByOrganizationId(Long actorId);
    Event findByIdAndOrganizationId(Long productId, Long actorId);

    // Some extra methods
    <LocalDateTime> List<Event> findByOrganizationIdAndEventDateAfter(Long organizationId, LocalDateTime date);
    List<Event> findByLocationAndMaxParticipantsGreaterThan(String location, int maxParticipants);


    List<Event> findAllPendingEvents();
}
