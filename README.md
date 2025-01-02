# **Voluntia** 

---
## Project Overview

**Voluntia** is a comprehensive platform built to manage volunteers, events, and administrative tasks. It provides different roles such as **Admin**, **Volunteer**, and **Organization**, each with distinct privileges. The system allows Admin users to manage user roles and approve events, while Volunteers can sign up, participate in events, and manage their own profiles. Organizations can create events and track volunteer participation.

Built using **Spring Boot** and **JPA** for database interaction, this platform also integrates **H2** as an in-memory database for easy development and testing. The project follows a **RESTful** API architecture for seamless interaction between the frontend and backend.

---

## Key Features

### **Admin Authentication & Management**:
1. **Admin Role**:
  - The **Admin** role is the central authority responsible for managing users, approving events, and handling login and logout functionality.
  - Admin can sign up only if no other admin is currently logged in.
  - Admin can log in, log out, and delete their account after verifying credentials.

2. **User & Event Management**:
  - Admin has the ability to approve or deny new users and events.
  - Admin can view and edit their own profile information.

### **Volunteer & Organization Role Management**:
- **Volunteer**: Volunteers can sign up, participate in events, and manage their profiles.
- **Organization**: Organizations create events and manage the participation of volunteers.

---

## **Roles and System Architecture**

The system is built around different **roles** with specific responsibilities and permissions. These roles inherit from a common **User** superclass, and each role can interact with various **system classes** that manage the business logic and data.

### **Roles - Users**

1. **User** (Superclass for all roles)
  - **Description**: A base class that holds the common properties for all user types (Admin, Volunteer, Organization).
  - **Common Properties**:
    - `id`: Unique identifier for each user.
    - `username`: The username of the user.
    - `password`: The password for user authentication.
    - `email`: The email address of the user.
    - `firstName`: The first name of the user.
    - `lastName`: The last name of the user.
    - `accountKey`: Special admin key for verifying admin actions.
    - `isLoggedIn`: Boolean indicating whether the user is logged in.

2. **Admin**:
  - **Description**: The **Admin** role manages the platform, approves users and events, and controls other administrative actions.
  - **Responsibilities**:
    - Approve new users and events.
    - View and edit own profile information.
    - Log in and log out from the system.

3. **Volunteer**:
  - **Description**: A **Volunteer** can participate in events, track their activities, and manage their own profile.
  - **Responsibilities**:
    - Sign up for events and participate in them.
    - Edit their profile information (e.g., personal details).
    - View a list of past and upcoming events they have participated in.

4. **Organization**:
  - **Description**: An **Organization** creates and manages events, tracks volunteer participation, and interacts with volunteers.
  - **Responsibilities**:
    - Create new events and manage their status.
    - View a list of volunteers participating in events.

---

### **System Classes**

1. **Event**:
  - **Description**: The **Event** class represents an event that is created by an organization and can be approved by an admin. Volunteers can participate in these events.
  - **Key Properties**:
    - `id`: Unique identifier for the event.
    - `name`: The name of the event.
    - `description`: A detailed description of the event.
    - `location`: The location where the event is held.
    - `date`: The date and time of the event.
    - `status`: The approval status of the event (approved or pending).

2. **Participation**:
  - **Description**: **Participation** represents the relationship between a **Volunteer** and an **Event**, tracking which volunteer is participating in which event.
  - **Key Properties**:
    - `id`: Unique identifier for each participation record.
    - `volunteerId`: The ID of the volunteer participating in the event.
    - `eventId`: The ID of the event the volunteer is participating in.
    - `status`: The status of the participation (e.g., active, completed).

---

### **Administration Services**

1. **AuthenticationService**:
  - **Description**: This service handles all user authentication-related tasks such as sign up, log in, and log out.
  - **Key Methods**:
    - `signUp()`: Handles the process of creating a new admin account.
    - `logIn()`: Allows the admin to log in with their credentials.
    - `logOut()`: Logs the admin out of the system.
    - `deleteAccount()`: Deletes the admin account after validating the correct password and special key.

2. **UserService**:
  - **Description**: This service contains business logic related to user management, including profile management and event approval.
  - **Key Methods**:
    - `approveUser()`: Allows an admin to approve a new user.
    - `approveEvent()`: Allows an admin to approve a new event.
    - `displayProfileInfo()`: Displays the current profile details of an admin.
    - `editProfileInfo()`: Allows an admin to update their profile information.

---

## **Folder Structure**

This is how the codebase is organized:

```
voluntia/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── gr/
│   │   │       └── dit/
│   │   │           └── voluntia/
│   │   │               └── demo/
│   │   │                   ├── controllers/         # REST API controllers for handling requests
│   │   │                   ├── services/            # Contains business logic services like authentication, user management
│   │   │                   ├── models/              # Data models for Admin, User, Event, etc.
│   │   │                   ├── repositories/        # Repositories for interacting with the database (CRUD operations)
│   │   │                   ├── dtos/                # Data Transfer Objects (DTOs) for communication with the client (requests & responses)
│   │   │                   └── DemoApplication.java  # Spring Boot entry point
│   │   └── resources/
│   │       ├── application.properties  # Configuration for database, security, etc.
│   │       ├── static/                 # Static resources like CSS, JavaScript
│   │       └── templates/              # Templates for rendering HTML views
├── README.md                    # Project documentation
└── LICENSE                       # License file for the project
```

### **Key Directories**:

- **controllers/**: Contains the REST API controllers like `EventController.java` and `UserController.java` for handling HTTP requests and routing.
- **models/**: Contains the entity classes such as `Admin`, `Volunteer`, `Event`, and `Participation` which are mapped to the database.
- **services/**: Contains the business logic for managing users, events, and admin functionalities (`AdminService.java`, `AuthenticationService.java`).
- **dtos/**: Contains the request and response DTOs (e.g., `SignUpRequest.java`, `ResponseMsg.java`).

---

## Installation and Setup

### 1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/voluntia.git
   ```

### 2. Navigate into the project directory:
   ```bash
   cd voluntia
   ```

### 3. Install dependencies using Maven:
   ```bash
   mvn install
   ```

### 4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

### Conclusion

This **Voluntia** project provides an easy-to-manage platform where volunteers, organizations, and admins can interact. The architecture is designed to be scalable, modular, and easy to extend with additional features as needed.