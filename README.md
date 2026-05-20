# Library Management System REST API

A RESTful backend API for managing books and library members, built using Java and Spring Boot.

## Features

- Add books to the library
- Retrieve all books
- Track book availability
- Register library members
- Borrow and return books
- Persistent storage using Java serialization
- REST API architecture with JSON request/response handling

## Technologies Used

- Java
- Spring Boot
- Maven
- REST APIs
- JSON
- Postman
- Git/GitHub

## API Endpoints

### Get all books

GET /books

### Add a book

POST /books

Example JSON body:

```json
{
  "title": "The Hobbit",
  "author": "J.R.R. Tolkien",
  "id": 3
}
```
## Running the Project

- Clone Github Repository 
- Navigate into the project directory
- Run the application 
- Open in browser or Postman


## Future Improvements 

- Add member API endpoints
- Add DELETE and PUT endpoints
- Connect to a database using Spring Data JPA
- Add Authentication and security 
- Deploy API to the cloud 


