package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.services.blueprints.ActionBasedProductsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements ActionBasedProductsService<Event> {

    private EventRepository eventRepository;

    // Implemented methods from the blueprints/ActionBasedProductsService interface

    @Override
    public Event findProductById(Long productId) {
        return eventRepository.findById(productId).orElse(null);
    }

    @Override
    public Event findProductForUser(Long actorId, Long productId) {
        return eventRepository.findByIdAndOrganizationId(productId, actorId);
    }

    @Override
    public List<Event> findAllProductsForUser(Long actorId) {
        return eventRepository.findAllByOrganizationId(actorId);
    }

    @Override
    public Event saveProduct(Event product) {
        return eventRepository.save(product);
    }

    @Override
    public Event deleteProduct(Event product) {
        eventRepository.delete(product);
        return product;
    }

    @Override
    public Event updateProduct(Event product) {
        Optional<Event> existing = eventRepository.findById(product.getId());

        if (existing.isPresent()) {
            Event updated = existing.get();
            updated.setName(product.getName());
            updated.setDescription(product.getDescription());
            updated.setLocation(product.getLocation());
            updated.setDate(product.getDate());
            updated.setMaxNumbOfVolunteers(product.getMaxNumbOfVolunteers());
            updated.setStatus(product.getStatus());
            return eventRepository.save(updated);
        }
        return null;
    }

    @Override
    public List<Event> saveProducts(List<Event> products) {
        return eventRepository.saveAll(products);
    }

    @Override
    public List<Event> deleteProducts(List<Event> products) {
        return products.stream()
                .map(this::updateProduct)
                .toList();
    }

    @Override
    public List<Event> updateProducts(List<Event> products) {
        eventRepository.deleteAll(products);
        return products;
    }


    // Extra methods for Event's Use case specific
    public List<Event> findAllEventsWithName(String name) {
        return eventRepository.findAllByName(name);
    }


}


