package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long>,
        JpaSpecificationExecutor<Participation> {


    Participation findByVolunteerIdAndEventId(Long actorId, Long productId);
    List<Participation> findAllByVolunteerId(Long actorId);
    List<Participation> findAllByEventId(Long eventId);


    // Custom queries for more complex activities
    @Query(
    "SELECT p " +
    "FROM Participation p " +
     "WHERE p.status = 'Pending' " +
            "AND p.organizationId = :organizationId"
    )
    List<Participation> findPendingParticipationsForOrganization(
            @Param("organizationId") Long organizationId
    );

}
