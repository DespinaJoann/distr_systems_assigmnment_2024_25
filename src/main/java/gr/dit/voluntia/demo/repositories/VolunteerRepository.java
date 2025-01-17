
package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, JpaSpecificationExecutor<Volunteer> {

    @Query("SELECT v FROM Volunteer v WHERE v.username = :username")
    Volunteer findByUsername(@Param("username") String username);

    // Some extra methods
    List<Volunteer> findByIsLoggedInFalse();

    @Query("SELECT vol FROM Volunteer vol WHERE vol.accountStatus = 'pending'")
    List<Volunteer> findAllPendingVolunteers();

    @Query("SELECT vol FROM Volunteer vol WHERE vol.accountStatus = 'approved'")
    List<Volunteer> findAllApprovedVolunteers();

    @Query("SELECT vol FROM Volunteer vol WHERE vol.accountStatus = 'rejected'")
    List<Volunteer> findAllRejectedVolunteers();

    Collection<Object> findByEmail(String email);


    User findByUsernameAndPassword(String username, String password);

    boolean existsAllByUsername(String username);
}