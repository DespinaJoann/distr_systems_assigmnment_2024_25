package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    Organization findByUsernameAndPassword(String username, String password);
    // TODO: ...
}