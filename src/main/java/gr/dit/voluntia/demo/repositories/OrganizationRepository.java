package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long>,
        JpaSpecificationExecutor<Organization> {

    Organization findByUsernameAndPassword(String username, String password);

    // Some extra - general methods
    @Query("SELECT o FROM Organization o WHERE o.username = :username")
    Organization findByUsername(@Param("username") String username);
    Organization findByEmail(String email);

    @Query("SELECT org FROM Organization org WHERE org.accountStatus = 'approved'")
    List<Organization> findAllApprovedOrganizations();

    @Query("SELECT org FROM Organization org WHERE org.accountStatus = 'rejected'")
    List<Organization> findAllRejectedOrganizations();

    @Query("SELECT org FROM Organization org WHERE org.accountStatus = 'pending'")
    List<Organization> findAllPendingOrganizations();
    boolean findByOrgName(String orgName);
}