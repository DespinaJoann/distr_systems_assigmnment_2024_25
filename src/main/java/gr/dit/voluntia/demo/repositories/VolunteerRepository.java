
package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, JpaSpecificationExecutor<Volunteer> {

    Volunteer findByUsernameAndPassword(String username, String password);
    // TODO: ...

}