# QAs: Questions and Answers:

### **Questions and Answers that we gave *(while developing the Web-Restful-App)*:**
#### 1. How many controllers do we need for our application? Do we need one for each entity and only one for the User *(or a main - base controller for the User and then some others that extend it for the subclasses)*?
   - Keep in mind that the User is a base class that will not be implemented as a table! But only will be extended by the subclasses and all its attributes will be written to the tables of them using the (`INHERITANCE.Strategy=JOINED`).
   - What is better and more appropriate and why ?(!)
---
In a Spring Boot application, controllers act as entry points for handling HTTP requests. Given that our system uses the User class as a base class with the JOINED inheritance strategy, we need to decide how to organize our controllers to balance clarity, maintainability, and performance.


2. The `dtos` *- Data Transferred Objects* will be only the **request / responses** of the `REST API`, or and something further? And why?
3. How could we actually apply the `CRUD` logic to our specific app?
4. How can we create our backend and implement it and run and test it without frontend settings, due to the fact that we will focus on developing the fronted after using the `REACT JS` framework and `JS`?
5. So how do we must develop our frontend and actually what is going on with the Participation class? How must we implement all the logic? Do we need to create a reposiory for this table ? How we will connect many-to-many / one-to-many? What we will do?
6. Do we need to create controllers for tge Event and Participation entities? What is better for our app and what is the appropriate in general?
