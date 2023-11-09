# Grievance Management System

The Grievance Management System is a web application designed to handle complaints and grievances in an organized 
and efficient manner. This system provides a platform for users to submit their grievances, track their status, 
and for administrators to manage and resolve these grievances.

## Features

- User Authentication: Users can log in using their credentials, ensuring secure access to the system.
- Grievance Submission: Users can submit complaints and grievances, providing essential details such as the title, description, and attachments.
- Categorization: Grievances can be categorized into different departments and categories to streamline the resolution process.
- Status Tracking: Users can track the status of their grievances, whether they are pending, resolved, or in progress.
- Comments and Resolutions: Users and administrators can add comments and resolutions to the grievances, facilitating communication.
- User Roles: The system supports different user roles, including regular users and administrators.
- Integration with User Management: User authentication is integrated with a separate User Management Service.

## Technologies Used

- Java
- Spring Boot
- H2 Database (for development)
- MySQL (for production)
- OpenAPI 3 for API documentation
- JWT for authentication

## Installation

1. Clone the repository.
2. Build the project using Maven: `mvn clean install`.
3. Run the application: `java -jar target/grievance-management-system-1.0.0.jar`.

Ensure you have Java and Maven installed on your system.

## Usage

1. Access the application through your web browser.
2. Log in using your credentials.
3. Submit a grievance with necessary details.
4. Track the status of your grievances.
5. Administrators can log in to manage and resolve grievances.

## API Documentation
-Provided Postman Collection and API Documentation in the repository.

## API Order
1. Create Department
2. Create Category for department
3. Create Grievance by using the category id
4. Add comment to the grievance
5. Update Grievance status

## Execute using Docker
1. Build the project using Maven: `docker build -t grievance-service:1.0 .`.
2. Run the application: `docker run -p 8080:8080  -e EUREKA_SERVER_URL=<EUREKA_SERVER_URL> grievance-service:1.0`.
