# To Do App Backend
This repository shares the code of the Backend for a To Do's Application. This project used **Maven** to setup the project, and the **Spring-Boot** framework to make the backend. 
### Run project
You can run this project with the comand: ***mvn spring-boot:run***, or you can use the ***Run Button*** from the IntelliJ IDEA IDE. 

### Functionality Update (07/08/2024)
- The project doesn't have a UI, so you'l get an error on the link of localhost:9090 while it's running. 
- I recommend using Postman to check the request of the API. 
- The PUT request to create a new To Do didn't work by sending empty the Due Date (LocalDate instance) value. 
- The tests of the project aren't implemented yet. 
