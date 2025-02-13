# :seedling: **Voluntia**

## **Project Overview**
**Voluntia** is a web-based platform developed as part of the course *"Distributed Systems"* at Harokopio University of Athens (HUA) during the academic year 2024-2025.
Authored by *Despina Ioanna Chalkiadaki* and *Vasiliki Maria Koutsi*, this project delivers a robust backend system designed for managing volunteers, events, and organizations. Built with **Spring Boot**, it utilizes **Hibernate ORM** for seamless database operations with **PostgreSQL** and ensures secure user authentication and authorization via **Spring Security**.
The platform provides core API endpoints for handling user and event operations, making it a reliable solution for volunteer management.

---

## **Abstract**
Welcome to **Voluntia**, the ultimate platform for fostering community-driven initiatives. Whether you’re seeking to contribute your skills, collaborate with like-minded volunteers, or organize impactful events, Voluntia is here to empower you. By connecting individuals and organizations passionate about making a difference, Voluntia creates opportunities to engage, inspire, and act for positive change.

---

## **Features**

### **Authentication & Authorization**
- Secure role-based access control using **Spring Security** (Admin, Volunteer, Organization roles).
- User registration, login, and logout functionalities with **BCrypt**-encrypted credentials.

### **Role-Based Functionalities**
1. **Admin**:
   - Approve or reject new user registrations (Volunteers and Organizations).
   - Approve or reject event submissions.
   - Manage user profiles and oversee platform activity.
2. **Volunteer**:
   - Browse available events and apply to participate.
   - Manage personal profile and track event participation.
3. **Organization**:
   - Create, update, and manage events.
   - Review and approve volunteer applications for events.
   - Maintain and update organizational profile.

### **Event & Participation Management**
- Organizations can manage events, including creating and monitoring them.
- Volunteers can register for events and track their involvement.

---

## **Tech Stack**

### **Backend**:
- **Java**: Primary programming language.
- **Spring Boot**: Framework for rapid and efficient backend development.
- **Hibernate ORM**: Simplifies database interactions.
- **PostgreSQL**: Relational database management system.
- **Spring Security**: Provides secure authentication and authorization.
- **Maven**: Manages dependencies and builds.

### **Frontend**:
- **Thymeleaf**: Template engine for dynamic content rendering.
- **HTML/CSS**: For structure and styling.
- **JavaScript**: Adds interactivity to the platform.

---

## **Default Settings**

### **Database Configuration**
Update the application.properties file with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voluntia
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### **Spring Security**
- Role-based access control for ADMIN, VOLUNTEER, and ORGANIZATION.
- Passwords are securely encrypted using **BCrypt**.

---

## **Setup & Run**

### **Prerequisites**
- **Java 17** or higher installed.
- **PostgreSQL** with a database named voluntia.
- Update your application.properties with the appropriate database credentials.

### **Run Locally**
1. Clone the repository:
   ```bash
   git clone https://github.com/DespinaJoann/distr_systems_assigmnment_2024_25.git
   cd distr_systems_assigmnment_2024_25
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   ```
   http://localhost:8080
   ```

### **Sample Data**
Previously, sample data was loaded using a dummy_data.sql file. Now, data initialization is handled programmatically via the **DataInitializer** class in the `gr.dit.voluntia.demo.config` package. The new approach ensures that the database is populated only when it is empty, providing a more efficient and dynamic solution.

#### **New Data Insertion Method:**
The `DataInitializer` class implements `CommandLineRunner` and checks if the database is empty before inserting sample data. It uses `EntityManager` for executing native SQL queries and **BCrypt** for password encryption. The initialization includes:

- **Admin Users**
- **Volunteers**
- **Organizations**
- **Events**

#### **Example Code:**
```java
@Component
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) {
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
}
```

This ensures that the database is only populated when necessary and avoids duplicate data entries.

---

## **TODO**
- Improve the UI and the design of the calendar page.
- Add more sample-data for testing purposes.E
---

## **License**
This project is licensed under the **Apache-2.0 License**.

