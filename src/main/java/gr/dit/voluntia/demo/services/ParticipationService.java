package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.Participation;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.services.blueprints.ActionBasedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService implements ActionBasedProductsService<Participation> {

    @Autowired
    private ParticipationRepository participationRepository;

    // Implemented methods

    /**Description:
     * Find the Participation with that participation_id*/
    @Override
    public Participation findProductById(Long productId) {
        return participationRepository.findById(productId).orElse(null);
    }

    /**Description:
     * Find the Participation of that Volunteer and that Event*/
    @Override
    public Participation findProductForUser(Long actorId, Long productId) {
        return participationRepository.findByVolunteerIdAndEventId(
                actorId, productId
        );
    }

    /**Description:
     * Find All Participations from that User */
    @Override
    public List<Participation> findAllProductsForUser(Long actorId) {
        return participationRepository.findAllByVolunteerId(actorId);
    }

    /**Description:
     * Save new participation to the Repository */
    @Override
    public Participation saveProduct(Participation product) {
        return participationRepository.save(product);
    }

    /**Description:
     * Delete an existing participation from the repository */
    @Override
    public Participation deleteProduct(Participation product) {
        participationRepository.delete(product);
        return product;
    }

    @Override
    public Participation updateProduct(Participation product) {
        // First things first, check if Participation Exists
        Optional<Participation> existing = participationRepository.findById(product.getId());

        if (existing.isPresent()) {
            Participation updated = existing.get();
            updated.setVolunteerId(product.getVolunteerId());
            updated.setEventId(product.getEventId());
            updated.setDate(product.getDate());
            updated.setStatus(product.getStatus());
            return participationRepository.save(updated);
        }
        return null;
    }

    @Override
    public List<Participation> saveProducts(List<Participation> products) {
        return participationRepository.saveAll(products);
    }

    @Override
    public List<Participation> deleteProducts(List<Participation> products) {
        participationRepository.deleteAll(products);
        return products;
    }

    @Override
    public List<Participation> updateProducts(List<Participation> products) {
        return products.stream()
                .map(this::updateProduct)
                .toList();
    }

    // Extra methods
    public List<Participation> findAllParticipationForEvent(Long eventId) {
        return participationRepository.findAllByEventId(eventId);
    }

}


