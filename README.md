
# **Voluntia**

## **Project Overview**
**Voluntia** is a backend solution developed as part of the course "Distributed Systems" at Harokopio University of Athens (HUA) during the academic year 2024-2025.
Authored by *Despina Ioanna Chalkiadaki* and *Vasiliki Maria Koutsi*, this project provides a robust backend system for managing volunteers, events, and organizations. Built using Spring Boot, it integrates Hibernate ORM for seamless database interactions with PostgreSQL and ensures secure authentication with Spring Security.
This API powers a volunteer management platform by offering core endpoints for user and event operations.

---

## **Features**

### **Authentication & Authorization**
- **Spring Security** for role-based access control (Admin, Volunteer, Organization).
- User registration, login, and logout with **BCrypt**-encrypted credentials.

### **Role-Based Functionalities**
1. **Admin**:
    - Approve/reject user registrations and event proposals.
    - Oversee platform activity and manage user roles.

2. **Volunteer**:
    - Sign up for events and track participation history.
    - Update and manage their profile.

3. **Organization**:
    - Create, update, and track events.
    - Monitor volunteer registrations and event engagement.

### **Event & Participation Management**
- Organizations can manage events, including scheduling and volunteer tracking.
- Volunteers register and participate in events, with activity logged for tracking purposes.

---

## **Tech Stack**
- **Java**: Core language.
- **Spring Boot**: Backend framework for rapid development.
- **Hibernate ORM**: Handles database persistence.
- **PostgreSQL**: Relational database.
- **Spring Security**: Manages authentication and authorization.
- **Maven**: Dependency and build management tool.

---

## **Directory Structure**

```plaintext
src/
├── main/
│   ├── java/
│   │   └── gr/
│   │       └── dit/
│   │           └── voluntia/
│   │               ├── config/                       # Security configurations
│   │               ├── controllers/                 # REST API controllers
│   │               ├── dtos/                        # Data Transfer Objects (DTOs)
│   │               │   ├── requests/                # Request DTOs
│   │               │   └── responses/               # Response DTOs
│   │               ├── exceptions/                  # Custom exception handling
│   │               ├── models/                      # Database entities
│   │               ├── repositories/                # Data access layer
│   │               ├── services/                    # Business logic
│   │               │   ├── blueprints/              # Abstract service definitions
│   │               └── utils/                       # Utility classes
│   └── resources/
│       ├── application.properties                   # Configuration files
│       ├── static/                                  # Static files
│       └── templates/                               # HTML templates
└── test/
    ├── java/                                        # Unit and integration tests
```

---

## **System Design**

### **Roles & Responsibilities**
1. **User**:
    - Base class for `Admin`, `Volunteer`, and `Organization`.
    - Common properties: `id`, `username`, `password`, `email`, `role`.

2. **Admin**:
    - Platform moderator with the ability to approve/reject users and events.

3. **Volunteer**:
    - Event participant who can manage their profile and view participation history.

4. **Organization**:
    - Event creator responsible for managing activities and tracking engagement.

### **Event and Participation**
1. **Event**:
    - Represents an activity created by an organization.
    - Properties: `id`, `name`, `description`, `location`, `date`, `status`.

2. **Participation**:
    - Links volunteers to events.
    - Tracks participation details such as `volunteerId`, `eventId`, and `status`.

---

## **Configuration**

### **Database**
Update `application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voluntia
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### **Spring Security**
- Role-based access: `ADMIN`, `VOLUNTEER`, `ORGANIZATION`.
- Passwords are encrypted using **BCrypt**.

---

## **Setup & Run**

### **Prerequisites**
- **Java 17** or later installed.
- **PostgreSQL** with a database named `voluntia`.
- Configure `application.properties`.

### **Run Locally**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/voluntia.git
   cd voluntia
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open the application in your browser:
   ```
   http://localhost:8080
   ```

---

## **Future Enhancements**
- **UI Integration**: Connect the backend with a frontend interface.
- **Notifications**: Add real-time updates for event changes using WebSockets.
- **Logging**: Implement **Spring Boot Actuator** for better monitoring.
