package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Finds events by applying optional filters for status, date,
     * topic and location.
     *
     * @param status The status of the event (e.g., "Confirmed").
     * @param date   The date of the event (optional).
     * @param topic  The topic of the event (optional).
     * @return A list of events matching the specified filters.
     * */
    @Query(
     "SELECT ev " +
     "FROM Event ev " +
     "WHERE ev.status = :status " +
     "AND (:date IS NULL OR ev.date = :date)" +
     "AND (:topic IS NULL OR ev.topic = :topic)" +
     "AND (:location IS NULL OR ev.location = :location)"
    )
    List<Event> findEventsByFilters(
            @Param("status") String status,
            @Param("date") String date,
            @Param("topic") String topic,
            @Param("location") String location
    );

    @Query(
    "SELECT ev " +
    "FROM Event ev " +
    "WHERE ev.status = :status "
    )
    List<Event> findEventsByStatus(String status);

    Event findByName(String evName);
}
