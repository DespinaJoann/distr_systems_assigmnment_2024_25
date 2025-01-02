package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long>,
        JpaSpecificationExecutor<Participation> {


    Participation findByVolunteerIdAndEventId(Long actorId, Long productId);
    List<Participation> findAllByVolunteerId(Long actorId);
    List<Participation> findAllByEventId(Long eventId);

}
