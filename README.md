# movie-review
Movie Review Platform


Movie Review Backend Application
📌 Project Description

The Movie Review Backend Application is a RESTful API built using Spring Boot.
It allows users to:

Add and manage movies

Post reviews for movies

Fetch reviews and ratings

Search for movies

This backend is designed for scalability and can serve as the foundation for a full-stack movie review platform.

⚙️ Setup & Installation
Prerequisites

Java 17+

Maven 3.8+

MySQL (or H2 for testing)

Git

Steps to Run Locally

Clone the repository:

git clone https://github.com/your-username/movie-review-backend.git
cd movie-review-backend


Update database configuration in src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/moviereview
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Build and run the application:

mvn clean install
mvn spring-boot:run


The backend will be available at:

http://localhost:8080

🧪 Running Test Cases

To execute test cases:

mvn test


This will run all unit tests and integration tests.
You can find test cases under src/test/java/com/movie/review/.

📝 Assumptions & Design Choices

Framework: Spring Boot was chosen for rapid development and REST API support.

Database: MySQL is the primary database; H2 is used for testing.

Entity Relationships:

A Movie can have multiple Reviews.

Each Review is linked to a User.

Security: For simplicity, authentication is not implemented in this version (can be added later using Spring Security + JWT).

Error Handling: Custom exceptions and @ControllerAdvice are used for clean error responses.

