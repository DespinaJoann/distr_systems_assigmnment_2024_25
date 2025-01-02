package gr.dit.voluntia.demo.services.blueprints;

import java.util.List;

/**
 * Description:
 * A Base blueprint for classes that are products of other role-classes
 * actions.
 * - (Participation, Event) will be the Services tha will
 * implement this interface. */
public interface ActionBasedProductsService<T> {

    // Queries
    /////////////////////////////////////////////////////////////////////

    // ?one
    T findProductById(Long productId);
    T findProductForUser(Long actorId, Long productId);

    // ?all
    List<T> findAllProductsForUser(Long actorId);

    // Commands
    /////////////////////////////////////////////////////////////////////

    // ?one
    T saveProduct(T product);
    T deleteProduct(T product);
    T updateProduct(T product);
    // ?all
    List<T> saveProducts(List<T> products);
    List<T> deleteProducts(List<T> products);
    List<T> updateProducts(List<T> products);
}


