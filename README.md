
# **Voluntia**

## **Project Overview**
**Voluntia** is a backend solution developed as part of the course "Distributed Systems" at Harokopio University of Athens (`HUA`) during the academic year 2024-2025.

Authored by *Despina Ioanna Chalkiadaki* and *Vasiliki Maria Koutsi*, this project provides a robust backend system for managing volunteers, events, and organizations. Built using `Spring Boot`, it integrates `Hibernate ORM` for seamless database interactions with `PostgreSQL` and ensures secure authentication with `Spring Security`.

This `API` powers a volunteer management platform by offering core endpoints for user and event operations.

#### The fullstack schema:
```zsh
.
├── demo.iml
├── HELP.md
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── gr
│   │   │       └── dit
│   │   │           └── voluntia
│   │   │               ├── config
│   │   │               │   └── security
│   │   │               ├── controllers
│   │   │               │   ├── AuthenticationController.java
│   │   │               │   ├── HomeController.java
│   │   │               │   └── ReactRoutingForwardingController.java
│   │   │               ├── DemoApplication.java
│   │   │               ├── linkers
│   │   │               │   ├── admin
│   │   │               │   │   ├── ConfirmEventsDto.java
│   │   │               │   │   └── ConfirmUserDto.java
│   │   │               │   ├── auths
│   │   │               │   │   ├── DeleteDto.java
│   │   │               │   │   ├── LogOutDto.java
│   │   │               │   │   ├── SignInDto.java
│   │   │               │   │   └── SignUpDto.java
│   │   │               │   ├── glob
│   │   │               │   │   ├── DisplayProfileDto.java
│   │   │               │   │   └── EditProfileInfoDto.java
│   │   │               │   ├── org
│   │   │               │   │   ├── CreateNewEventDto.java
│   │   │               │   │   └── DisplayParticipationListsDto.java
│   │   │               │   └── vols
│   │   │               │       ├── ApplyToEventDto.java
│   │   │               │       └── DisplayEventsDto.java
│   │   │               ├── exceptions
│   │   │               │   └── UsernameNotFoundException.java
│   │   │               ├── models
│   │   │               │   ├── Admin.java
│   │   │               │   ├── Event.java
│   │   │               │   ├── Organization.java
│   │   │               │   ├── Participation.java
│   │   │               │   ├── User.java
│   │   │               │   └── Volunteer.java
│   │   │               ├── repositories
│   │   │               │   ├── AdminRepository.java
│   │   │               │   ├── EventRepository.java
│   │   │               │   ├── OrganizationRepository.java
│   │   │               │   ├── ParticipationRepository.java
│   │   │               │   └── VolunteerRepository.java
│   │   │               ├── services
│   │   │               │   ├── AdminService.java
│   │   │               │   ├── AuthenticationRooter.java
│   │   │               │   ├── blueprints
│   │   │               │   │   ├── ActionBasedProductsService.java
│   │   │               │   │   ├── AuthenticationService.java
│   │   │               │   │   └── UserService.java
│   │   │               │   ├── EventService.java
│   │   │               │   ├── OrganizationService.java
│   │   │               │   ├── ParticipationService.java
│   │   │               │   └── VolunteerService.java
│   │   │               └── utils
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       │   ├── css
│   │       │   │   └── main.[hash].css
│   │       │   ├── js
│   │       │   │   ├── main.[hash].js
│   │       │   │   └── runtime-main.[hash].js
│   │       │   └── index.html
│   │       
│   └── test
│       └── java
│           └── gr
│               └── dit
│                   └── voluntia
│                       └── demo
│                           └── DemoApplicationTests.java
└── target
    ├── classes
    ├── test-classes
```