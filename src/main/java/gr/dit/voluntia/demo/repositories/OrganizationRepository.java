package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long>,
        JpaSpecificationExecutor<Organization> {

    Organization findByUsernameAndPassword(String username, String password);

    // Some extra - general methods
    Organization findByUsername(String username);
    Organization findByEmail(String email);

    List<Organization> findAllPendingOrganizations();

    boolean findByOrgName(String orgName);
}