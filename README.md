![Java 17+](https://img.shields.io/badge/Java-17%2B-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Relational%20Database-brightgreen) ![Config](https://img.shields.io/badge/Update-application.properties-pink) ![Java](https://img.shields.io/badge/Java-Primary%20Language-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend%20Framework-cyan) ![Hibernate ORM](https://img.shields.io/badge/Hibernate-ORM-orange) ![Spring Security](https://img.shields.io/badge/Spring%20Security-Authentication%20%26%20Authorization-darkgreen) ![Maven](https://img.shields.io/badge/Maven-Build%20Tool-yellow) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template%20Engine-blueviolet) ![HTML/CSS](https://img.shields.io/badge/HTML%20%2F%20CSS-Frontend-orange) ![JavaScript](https://img.shields.io/badge/JavaScript-Interactivity-yellow)

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
Update the `application.properties` file with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voluntia
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### **Spring Security**
- Role-based access control for `ADMIN`, `VOLUNTEER`, and `ORGANIZATION`.
- Passwords are securely encrypted using **BCrypt**.

---

## **Setup & Run**

### **Prerequisites**
- **Java 17** or higher installed.
- **PostgreSQL** with a database named `voluntia`.
- Update your `application.properties` with the appropriate database credentials.

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

4. Access the application:
   ```
   http://localhost:8080
   ```

### **Sample Data**
A `dummy_data.sql` file is included to populate the database with test data. The sample data has already been integrated into the database during setup for testing purposes.

---

## **TODO**

---

## **License**
This project is licensed under the **Apache-2.0 License**.
