
package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, JpaSpecificationExecutor<Volunteer> {

    Volunteer findByUsernameAndPassword(String username, String password);

    // Some extra methods
    List<Volunteer> findByIsLoggedInFalse();

    @Query("SELECT vol FROM Volunteer vol WHERE vol.accountStatus = 'pending'")
    List<Volunteer> findAllPendingVolunteers();

    Collection<Object> findByEmail(String email);
}