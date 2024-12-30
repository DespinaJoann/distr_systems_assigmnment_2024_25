
# Voluntia
### A Voluntary Actions Management System
## Sketch of the `Backend`.

##### The plan:
- **`Backend`**: `Spring Boot` (`Spring Data JPA` *aka: `Hibernate`* + `Spring Security` + `Postgres SQL`).
    - *The **API** is a Restful API that handles JSON requests*
- **`Frontend`**: `Node JS` + `React JS`.


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
    - **User:** Υπερκλάση για όλους τους τύπους χρηστών (Admin, Volunteer, Organization).
        - *Παρέχει κοινές ιδιότητες σε όλους τους τύπους χρηστών.*
    - **Admin:** Διαχειριστής που εγκρίνει χρήστες, εκδηλώσεις και εθελοντές.
    - **Volunteer:** Εθελοντής που συμμετέχει σε εκδηλώσεις και διαχειρίζεται τη συμμετοχή του.
    - **Organization:** Οργανισμός που δημιουργεί εκδηλώσεις και διαχειρίζεται τη συμμετοχή των εθελοντών.
- **System Classes:**
    - **Event:** Αναπαριστά μια εκδήλωση στην πλατφόρμα.
    - **Participation:** Συνδέει έναν Volunteer με μια Event, διαχειρίζοντας τη συμμετοχή του εθελοντή.
- **Administration Services:**
    - **AuthenticationService:**Παρέχει τη λογική αυθεντικοποίησης (login, signup, logout, κλπ.).

