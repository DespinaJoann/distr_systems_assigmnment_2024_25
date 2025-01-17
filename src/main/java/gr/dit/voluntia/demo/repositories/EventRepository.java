package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Remove all events from the list
    void deleteAllByIdIn(List<Long> ids);

    Event findByName(String evName);
    Event findByIdAndOrganizationId(Long productId, Long actorId);
    List<Event> findAllByName(String name);
    List<Event> findAllByOrganizationId(Long actorId);
    List<Event> findByOrganizationIdAndStatus(Long organizationId, String status);
    List<Event> findByOrganizationId(Long orgId);

    // Some extra methods
    @Query("SELECT ev FROM Event ev WHERE ev.status = 'pending'")
    List<Event> findAllPendingEvents();

    @Query("SELECT ev FROM Event ev WHERE ev.status = 'confirmed'")
    List<Event> findAllConfirmedEvents();

    @Query("SELECT ev FROM Event ev WHERE ev.status = 'rejected'")
    List<Event> findAllRejectedEvents();
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

    @Query(
            "SELECT ev " +
                    "FROM Event ev " +
                    "WHERE " +
                    "ev.status = :status AND ev.organizationId = :orgId"
    )
    List<Event> findEventsByOrgIdAndStatus(String status, Long orgId);


    Boolean existsByName(String name);
}
