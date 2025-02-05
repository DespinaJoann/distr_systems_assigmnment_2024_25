package gr.dit.voluntia.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional    public void run(String... args) {
        if (isDatabaseEmpty()) {
            insertAdmin();
            insertVolunteers();
            insertOrganizations();
            insertEvents();
            System.out.println("Database initialized with seed data.");
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }
    private boolean isDatabaseEmpty() {
        return isTableEmpty("Admin") && isTableEmpty("Volunteer") && isTableEmpty("Organization") && isTableEmpty("Event");
    }

    private boolean isTableEmpty(String tableName) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(e) FROM " + tableName + " e", Long.class);
        return query.getSingleResult() == 0;
    }

    private void insertAdmin() {
        entityManager.createNativeQuery("INSERT INTO Admin (username, password, email, first_name, last_name, role) VALUES (:username, :password, :email, :first_name, :last_name, :role)")
                .setParameter("username", "deva")
                .setParameter("password", passwordEncoder.encode("dev123!!"))
                .setParameter("email", "devmain@gmail.com")
                .setParameter("first_name", "Despina - Vasiliki")
                .setParameter("last_name", "Chalkiadaki - Koutsi")
                .setParameter("role", "ADMIN")
                .executeUpdate();
    }

    private void insertVolunteers() {
        insertVolunteer("alan", "123abc!!?", "alanHog@gmail.com", "Alan", "Hog", "6923108835", "1996-02-19", "I want to actively work to make the world a better place");
        insertVolunteer("emma", "em@123!!", "emmaGreen@gmail.com", "Emma", "Green", "6987654321", "1993-08-15", "Passionate about environmental conservation and social impact.");
        // Add more volunteers as needed
    }

    private void insertVolunteer(String username, String rawPassword, String email, String firstName, String lastName, String phoneNumber, String dateOfBirth, String profileDescription) {
        entityManager.createNativeQuery("INSERT INTO Volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status) VALUES (:username, :password, :email, :first_name, :last_name, :phone_number, :date_of_birth, :profile_description, 'VOLUNTEER', 'pending')")
                .setParameter("username", username)
                .setParameter("password", passwordEncoder.encode(rawPassword))
                .setParameter("email", email)
                .setParameter("first_name", firstName)
                .setParameter("last_name", lastName)
                .setParameter("phone_number", phoneNumber)
                .setParameter("date_of_birth", dateOfBirth)
                .setParameter("profile_description", profileDescription)
                .executeUpdate();
    }

    private void insertOrganizations() {
        insertOrganization("betterWorld22", "b3t3r4all!!", "better.world@gmail.org", "Better World", "6987672345", "1, D. Gounari Street 151 24 Marousi", "Marousi, Attica", "non-profit", "We focus on promoting environmental sustainability and humanitarian efforts.");
        // Add more organizations as needed
    }

    private void insertOrganization(String username, String rawPassword, String email, String orgName, String phoneNumber, String address, String location, String orgType, String profileDescription) {
        entityManager.createNativeQuery("INSERT INTO Organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status) VALUES (:username, :password, :email, :org_name, :phone_number, :address, :location, :org_type, :profile_description, 'ORGANIZATION', 'pending')")
                .setParameter("username", username)
                .setParameter("password", passwordEncoder.encode(rawPassword))
                .setParameter("email", email)
                .setParameter("org_name", orgName)
                .setParameter("phone_number", phoneNumber)
                .setParameter("address", address)
                .setParameter("location", location)
                .setParameter("org_type", orgType)
                .setParameter("profile_description", profileDescription)
                .executeUpdate();
    }

    private void insertEvents() {
        insertEvent("Second Attica Tree Planting Event", "A community event focused on planting trees to combat deforestation in the region.", "Parnasos, Attica, Greece", "Tree Planting", "2025-03-22", 1);
        // Add more events as needed
    }

    private void insertEvent(String name, String description, String location, String topic, String date, int organizationId) {
        entityManager.createNativeQuery("INSERT INTO Event (name, description, location, topic, date, number_of_volunteers, organization_id, status) VALUES (:name, :description, :location, :topic, :date, 0, :organization_id, 'pending')")
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("location", location)
                .setParameter("topic", topic)
                .setParameter("date", date)
                .setParameter("organization_id", organizationId)
                .executeUpdate();
    }
}