package gr.dit.voluntia.demo.repositories;

import gr.dit.voluntia.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    @Override
    void deleteById(Long aLong);

    Admin findByEmailAndPasswordAndAccountKey(String email, String password, String accountKey);
    Admin findByUsernameAndPasswordAndAccountKey(String username, String password, String accountKey);
    Admin findByIsLoggedInTrue();

}