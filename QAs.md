# QAs: Questions and Answers:

### **Questions and Answers that we gave *(while developing the Web-Restful-App)*:**

#### 1. How many controllers do we need for our application? Do we need one for each entity and only one for the User *(or a main - base controller for the User and then some others that extend it for the subclasses)*?
   - Keep in mind that the User is a base class that will not be implemented as a table! But only will be extended by the subclasses and all its attributes will be written to the tables of them using the (`INHERITANCE.Strategy=JOINED`).
   - What is better and more appropriate and why ?(!)



**Analysis:**

In a Spring Boot application, controllers act as entry points for handling `HTTP` requests. Given that our system uses the User class as a base class with the `JOINED` inheritance strategy, we need to decide how to organize our controllers to balance **clarity, maintainability**, and **performance**.

- **Option 1: One Controller per Entity**  
   - Each role (`Admin`, `Volunteer`, `Organization`) has its own controller.  
   - The controllers directly map to their respective repositories and services.  
   - This is a simple structure where each role's functionality is entirely separated.  
   - Shared functionality like login could be handled in an additional `AuthenticationController`.  

- **Option 2: Base UserController with Role-Specific Controllers**  
   - A base `UserController` contains shared functionality (e.g., login, logout).  
   - Role-specific controllers (`AdminController`, `VolunteerController`, `OrganizationController`) extend this base controller or operate independently.  
   - This allows for centralized logic for common operations but still separates role-specific functionality.  

**Decision:**

To use **Option 2 (Base UserController + Role-Specific Controllers)** because:
- It centralizes shared operations, reducing code duplication (e.g., login).
- It allows for flexibility by letting each role-specific controller handle unique functionalities.
- It aligns with the principle of **single responsibility** while making the design modular and extensible.

---

#### 2. The `dtos` *- Data Transferred Objects* will be only the **request / responses** of the `REST API`, or and something further? And why?

**Analysis:**

**DTOs (Data Transfer Objects)** are primarily used to:
- Define the structure of data being sent to or received from the client.
- Decouple API contracts (requests and responses) from internal domain models (entities).
- Provide an additional layer for **data validation**, **transformation**, and **security**.  

DTOs in most systems serve as the **boundary** of your application, ensuring that:
1. The client sees only the data you want to expose.  
2. Validation rules are enforced early in the request lifecycle.  
3. Domain models (e.g., `Admin`, `Volunteer`) remain unaffected by changes in the API. 

**Decision:**

To keep DTOs focused on **request/response handling only** for:
1. SignUp, Login, Delete and Edit operations.
2. API responses to standardize data sent to clients.  
This keeps the design simple and avoids conflating DTOs with other responsibilities like database mapping.

---

#### 3. How could we actually apply the `CRUD` logic to our specific app?

**Analysis:**

CRUD (`Create`, `Read`, `Update`, `Delete`) forms the backbone of any application, and its implementation depends on how the entities are structured. In our system:
- Each role (`Admin`, `Volunteer`, `Organization`) is tied to a specific table due to the `JOINED` inheritance strategy.
- CRUD operations should be role-specific, while some shared operations (e.g., login) can be generalized.

1. **Create (`POST`)**  
   - Each role has a dedicated creation method (`signUp`).  
   - Use DTOs for validation to ensure only valid data is persisted.

2. **Read (`GET`)**  
   - Fetch user-specific or role-specific data (e.g., get all events for a volunteer).  
   - Add optional filters for more advanced queries (e.g., find events by date).

3. **Update (`PUT`/`PATCH`)**  
   - Allow role-specific updates (e.g., update an organization's address or a volunteer's contact info).  
   - Validate incoming data to avoid inconsistencies.

4. **Delete (`DELETE`)**  
   - Role-based deletion (e.g., delete an event as an admin or cancel participation as a volunteer).  
   - Handle dependencies carefully (e.g., removing participations tied to a deleted volunteer).

**Decision:**

Implement CRUD operations role-specific, with:
- Role-specific services (e.g., `VolunteerService`) handling logic.
- Validation using request DTOs.
- Consistency checks before deletion or updates (e.g., cascade behavior for related entities).

---

#### 4. How can we create our backend and implement it and run and test it without frontend settings, due to the fact that we will focus on developing the fronted after using the `REACT JS` framework and `JS`? 
- we use PostgreSQL , maybe we can do sth a command line runner? 

**Analysis:**
// TODO: ...
**Decision:**
// TODO: ...

---


#### 5. So how do we must develop our frontend and actually what is going on with the Participation class? How must we implement all the logic? Do we need to create a reposiory for this table ? How we will connect many-to-many / one-to-many? What we will do?

**Analysis:**

The `Participation` class links `Volunteer` and `Event` in a many-to-many relationship. It allows tracking of additional information about the relationship, such as the participation date or status.

1. **Database Relationships:**
   - Define `@ManyToOne` relationships in `Participation` to link it to `Volunteer` and `Event`.  
   - This is a classic many-to-many pattern with a join table (`Participation`).

2. **Repository and Service:**
   - Create a repository to manage `Participation` records (e.g., register for an event, cancel participation).  
   - Add methods to query participations by volunteer or event.

3. **Controller:**
   - Implement endpoints for registering a volunteer to an event, fetching participation details, etc.

**Decision:**

- Implement `Participation` as a full-fledged entity with a repository and service.  
- Use it to track relationships and additional details (e.g., status).  
- Create a dedicated `ParticipationController` to handle participation-specific endpoints.

---

#### 6. Do we need to create controllers for tge Event and Participation entities? What is better for our app and what is the appropriate in general?

**Analysis:**

Controllers provide endpoints for client interactions. For `Event` and `Participation`:
1. `EventController`:  
   - Handles CRUD operations for events (e.g., creating events, fetching event details).
   - Focuses on `Organization` and `Admin` interactions.

2. `ParticipationController`:  
   - Manages volunteer registrations for events.
   - Provides endpoints for fetching participations.

**Decision:**

- Create **both controllers** to encapsulate entity-specific logic and keep the design modular.  
- Use services to delegate business logic, keeping controllers lightweight.

---

### Generall Design!
// TODO: ...