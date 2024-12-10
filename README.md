# Treasure API

This API was developed using Java and Spring Boot for the **Love Safe** project, a web-based application designed to securely store and manage messages.

## Features

- **Message Storage**: Allows users to create, retrieve, update, and delete messages.
- **In-memory Database**: Uses H2 database for local storage, with automatic schema management via Hibernate.
- **RESTful Endpoints**: Provides RESTful API endpoints for managing messages.
- **Error Handling**: Implements custom exception handling for different error scenarios (e.g., invalid input, resource not found).
- **Database Console**: Integrated H2 console for direct database management during development.

## Technologies Used

- **Java 17**: The programming language used to develop the API.
- **Spring Boot 2.x**: Framework for building Java-based applications with minimal configuration.
- **Spring Data JPA**: Simplifies data access using JPA (Java Persistence API).
- **H2 Database**: In-memory database used for development and testing.
- **Hibernate**: ORM (Object-Relational Mapping) framework for managing the database schema.
- **Maven**: Dependency management and build automation tool.

## Setup and Installation

### Prerequisites

Ensure you have the following installed:

- **Java 17** or later
- **Maven**

### Running the Application

1. Clone the repository:
    ```bash
    git clone https://github.com/analarap/treasure-api.git
    ```

2. Navigate to the project directory:
    ```bash
    cd treasure-api
    ```

3. Build and run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

4. The API will be available at:
    ```
    http://localhost:8081
    ```

### H2 Database Console

- Access the H2 database console at:
    ```
    http://localhost:8081/h2-console
    ```
- Database URL: `jdbc:h2:mem:teste`
- Username: `sa`
- Password: `password`

## API Endpoints

### Create a Message
- **POST** `/messages`
- **Request Body**:
    ```json
    {
        "content": "Message content",
        "author": "Author Name"
    }
    ```
- **Response**: Status 201 Created, returns the created message.

### Get All Messages
- **GET** `/messages`
- **Response**: Returns a list of all stored messages.

### Get Message by ID
- **GET** `/messages/{id}`
- **Response**: Returns the message with the specified ID, or a 404 if not found.

### Update a Message
- **PUT** `/messages/{id}`
- **Request Body**:
    ```json
    {
        "content": "Updated message content",
        "author": "Updated Author Name"
    }
    ```
- **Response**: Returns the updated message, or a 404 if the message with the specified ID is not found.

### Delete a Message
- **DELETE** `/messages/{id}`
- **Response**: Status 204 No Content on success, or 404 if the message with the specified ID is not found.

Thinking of adding a front-end soon... stay tuned :)