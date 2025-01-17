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

    @Query (
    "SELECT p " +
    "FROM Participation p " +
    "WHERE " +
            "p.organizationId = :orgId " +
            "AND p.status = :status"
    )
    List<Participation> findParticipationsForOrganizationByStatus (
            @Param("orgId") Long orgId,
            @Param("status") String status
    );

    @Query (
            "SELECT p " +
                    "FROM Participation p " +
                    "WHERE " +
                    "p.volunteerId = :volId " +
                    "AND p.status = :status"
    )
    List<Participation> findParticipationsForVolunteerByStatus (
            @Param("volId") Long volId,
            @Param("status") String status
    );

    List<Participation> findByStatus(String rejected);




}
