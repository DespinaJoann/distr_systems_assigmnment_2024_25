# 🤔 **QAs: Questions and Answers**  
🔑 *Key Questions` We Faced While Developing the Web-Restful-App (and How We Solved Them):*  

---

### 1️. How many controllers do we need for our application?**Do we need one controller for each entity? Or should we have a main base controller for the `User` class and separate role-specific controllers for subclasses like `Admin`, `Volunteer`, and `Organization`?

💡 *Note:* The `User` class is abstract and won’t be directly mapped to a table. Instead, it’s extended by the subclasses, and its fields are written to their respective tables using the `INHERITANCE.Strategy=JOINED`.

#### **Analysis:**  
Controllers are the entry points for handling HTTP requests in our Spring Boot application. The challenge here is to balance **clarity, maintainability**, and **efficiency**.  

Here are the two main options we considered:  

- **Option 1: One Controller per Entity**  
   Each role (`Admin`, `Volunteer`, `Organization`) gets its own controller that directly maps to the corresponding repository and service. Shared functionality like login or logout is handled in a separate controller (e.g., `AuthenticationController`).  
   - **Advantages:**  
     - Clear separation of concerns.  
     - Each controller is focused on its specific role.  
   - **Disadvantages:**  
     - Duplicated code for shared functionality like login or logout.  
     - Potentially harder to maintain as the app grows.  

- **Option 2: Base UserController + Role-Specific Controllers**  
   A shared `UserController` handles functionality common to all roles (e.g., login, logout), while role-specific controllers (`AdminController`, `VolunteerController`, `OrganizationController`) manage their own unique operations.  
   - **Advantages:**  
     - Centralizes shared logic, making it easier to maintain.  
     - Reduces duplicate code.  
     - Still allows for role-specific flexibility where needed.  
   - **Disadvantages:**  
     - Slightly more complex to set up, as we need to differentiate between shared and role-specific logic.  


#### **Decision:**  
✅ We chose **Option 2 (Base UserController + Role-Specific Controllers)**. Here’s why:  
- Shared operations like login and logout are centralized, avoiding code duplication.  
- Each role can still have its own dedicated controller for unique functionalities.  
- The design is **modular** and **extensible**, making future updates or changes easier.  

For example:  
- `UserController` handles login and shared endpoints.  
- `AdminController` focuses on admin-specific tasks like approving events.  
- `VolunteerController` manages volunteer operations like viewing participations.  

---

### 2. What are DTOs (*Data Transfer Objects*) used for in our system?**Should we use DTOs only for API requests/responses, or could they serve other purposes?


#### **Analysis:**  
In a Spring Boot app, **DTOs** (Data Transfer Objects) are critical for separating API interactions from our domain logic.  

Here’s why they’re important:  
- **Primary Role:**  
   DTOs define the structure of data being sent to or received from the client. For example, when a user signs up or logs in, we use DTOs to validate and transfer that data.  
- **Why Not Use Entities Directly?**  
   Exposing entities directly can lead to:  
   - Security risks (e.g., exposing sensitive fields).  
   - Tight coupling between the API and the database schema.  
   - Difficulty in handling data validation and transformations.  


#### **Decision:**  
✅ We’ll use DTOs exclusively for **API requests and responses**, focusing on:  
1. **Requests** (e.g., `SignUpRequest`, `SignInRequest`) for user input validation.  
2. **Responses** (e.g., `UserDetailsResponse`, `EventDetailsResponse`) to control what data is returned to the client.  

This keeps our backend **secure**, **flexible**, and easy to maintain.

---

### 3️. How will we implement CRUD operations in our app? 


#### **Analysis:**  
CRUD operations are the foundation of any application. In our case, implementing them involves a few key considerations due to the `JOINED` inheritance strategy and role-based functionality.  

Here’s how we’ll handle each operation:  

1. **Create (`POST`)**  
   - Use **role-specific DTOs** to validate and map incoming data for creation.  
   - For example, creating a `Volunteer` requires different fields than creating an `Organization`.  

2. **Read (`GET`)**  
   - Provide endpoints for fetching data by ID or using filters (e.g., find events by date or location).  
   - Ensure role-based permissions (e.g., volunteers shouldn’t see admin-specific data).  

3. **Update (`PUT`/`PATCH`)**  
   - Validate incoming updates to avoid inconsistencies.  
   - Allow users to update their profiles while ensuring only authorized changes (e.g., an admin can approve events, but a volunteer can’t).  

4. **Delete (`DELETE`)**  
   - Handle deletions carefully to manage dependencies (e.g., deleting a volunteer should also remove their participations).  


#### **Decision:**  
✅ CRUD operations will be implemented as **role-specific services**.  
- Shared functionality will go into base services, while role-specific services (e.g., `AdminService`, `VolunteerService`) handle unique logic.  
- Repositories will focus solely on database interactions, keeping services responsible for business logic.  

---

### 4️. How can we test and run the backend without a frontend?  

We actually don't know exactly how yet :face_with_spiral_eyes: but we are styding and learning how we will do it :grin:.

#### **Possibilities:**  
-  Implement a `CommandLineRunner` to populate the database with test data for easier testing and creating dummy data for testing.

// TODO: ... Think more!

#### **Decision:**  
✅ We’ll focus on:  
- **Postman** for manual testing.  
- **Swagger UI** for interactive testing and documentation.  
- A mix of **H2 (development)** and **PostgreSQL (production)** for flexibility.

---

### 5️. How should we handle the `Participation` class and its relationships?  

#### **Analysis:**  
The `Participation` entity links `Volunteer` and `Event` in a many-to-many relationship. It’s also responsible for tracking additional information, like the participation status or registration date.  

Here’s the plan:  
1. **Database Relationships:**  
   - Define `@ManyToOne` relationships in `Participation` for both `Volunteer` and `Event`.  
   - Use `@JoinTable` to define the many-to-many relationship.  

2. **Repositories and Services:**  
   - Create a `ParticipationRepository` to handle CRUD operations.  
   - Implement a `ParticipationService` for logic like registering for an event or canceling participation.  

3. **Controllers:**  
   - Provide endpoints for volunteers to register for events, view their participations, and cancel them if needed.  


#### **Decision:**  
✅ The `Participation` entity will have its own repository, service, and controller. This ensures clean separation of logic and makes the relationship between `Volunteer` and `Event` easy to manage.

---

### 6️. Should we create controllers for Event and Participation entities?  

#### **Analysis:**  
Both `Event` and `Participation` require specific operations, so they each deserve their own controllers:  

1. **EventController:**  
   - Handles CRUD operations for events (e.g., creating, updating, or deleting events).  
   - Focuses on interactions by `Admin` and `Organization`.  

2. **ParticipationController:**  
   - Manages volunteer registrations for events.  
   - Handles fetching and managing participations.  


#### **Decision:**  
✅ Create separate controllers for `Event` and `Participation`. This keeps the design modular and ensures each controller has a clear purpose.  

---

### **General Design Takeaways:**  

1. 🚀 **DTOs** will handle all request/response validation and transformation.  
2. 🏗️ Role-specific **controllers and services** ensure a modular and maintainable structure.  
3. ⚙️ Focus on testing backend functionality with **Command line Runner**.  
4. 🔗 The `Participation` entity will manage many-to-many relationships and act as a bridge between `Volunteer` and `Event`.

