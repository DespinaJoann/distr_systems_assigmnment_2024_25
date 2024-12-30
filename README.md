# Voluntia  
### A Voluntary Actions Management System  

## Sketch of the `Backend`  

##### The Plan:  
- **`Backend`**: `Spring Boot` (`Spring Data JPA` *aka: `Hibernate`* + `Spring Security` + `Postgres SQL`)  
    - *The **API** is a Restful API that handles JSON requests*  
- **`Frontend`**: `Node JS` + `React JS`  

```zsh
voluntia-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── gr/
│   │   │       └── dit.voluntia.demo/
│   │   │           ├── config/
│   │   │           │   ├── security/          # Spring Security, JWT, CORS, etc.
│   │   │           ├── controllers/           # REST API Controllers (JSON responses)
│   │   │           ├── dtos/                  # Data Transfer Objects
│   │   │           ├── models/                # JPA Entities (Hibernate)
│   │   │           ├── exceptions/            # Custom exceptions
│   │   │           ├── repositories/          # Database Repositories (Spring Data JPA)
│   │   │           ├── services/              # Business Logic
│   │   │           └── utils/                 # Utility classes (e.g., JSON handling)
│   │   ├── resources/
│   │   │   ├── static/                        # Static resources (optional)
│   │   │   └── application.properties         # App configuration (Database, JWT, CORS, etc.)
│   └── test/
│       └── java/
│           └── gr/
│               └── hua.dit.event/
│                   └── ... (Test classes)
├── .gitignore                                    # Git ignore file
├── pom.xml                                       # Maven dependencies
├── README.md                                     # Project documentation
└── mvnw / mvnw.cmd                               # Maven wrapper scripts
```  

#### Models  

- **Roles - Users:**  
    - **User:** Superclass for all user types (Admin, Volunteer, Organization).  
        - *Provides common properties for all user types.*  
    - **Admin:** Administrator responsible for approving users, events, and volunteers.  
    - **Volunteer:** A volunteer who participates in events and manages their participation.  
    - **Organization:** An organization that creates events and manages volunteer participation.  

- **System Classes:**  
    - **Event:** Represents an event on the platform.  
    - **Participation:** Links a Volunteer to an Event, managing their participation.  

- **Administration Services:**  
    - **AuthenticationService:** Provides authentication logic (login, signup, logout, etc.).  