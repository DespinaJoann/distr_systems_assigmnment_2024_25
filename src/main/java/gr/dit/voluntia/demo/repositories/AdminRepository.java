package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    @Override
    void deleteById(Long aLong);

    Admin findByEmailAndPasswordAndAccountKey(String email, String password, String accountKey);
    Admin findByUsernameAndPasswordAndAccountKey(String username, String password, String accountKey);
    Admin findByIsLoggedInTrue();

    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    Admin findByUsername(@Param("username") String username);
}