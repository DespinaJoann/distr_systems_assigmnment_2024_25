package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.Participation;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService  {

    @Autowired
    private ParticipationRepository participationRepository;

    /**
     * Description:
     * Retrives all participations of a specific event.
     * @param evId the ID of the event.
     * @return List<Participation>the participation list
     * * */
    public List<Participation> findAllParticipationForEvent(Long evId) {
        return participationRepository.findAllByEventId(evId);
    }

}


