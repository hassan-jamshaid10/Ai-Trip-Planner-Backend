# AI Trip Planner Backend

## Overview
This project is the backend service for an AI-powered trip planner application, built using **Spring Boot**, **Spring Security 6**, and **JWT authentication**. It provides a secure and robust API for managing users, trips, destinations, bookings, and activities. The AI capabilities are powered by **Gemini AI** to enhance trip planning based on user preferences and historical data.

## Technologies
- **Spring Boot**: Java framework used for building the backend service.
- **Spring Security 6**: Provides security, authentication, and authorization features.
- **JWT Authentication**: Ensures secure user authentication using JSON Web Tokens.
- **Gemini AI**: AI technology that powers trip recommendations and planning features.
- **PostgreSQL**: Relational database management system used for storing and managing data.

## Entities

### 1. User
Represents a user of the trip planner application. Each user can create trips, manage bookings, and interact with various activities.

#### Fields:
- **id**: Unique identifier for the user.
- **username**: Username of the user.
- **email**: Email address of the user.
- **passwordHash**: Hashed password for secure authentication.
- **preferences**: User preferences related to trips and destinations.
- **trips**: List of trips created by the user.

### 2. Trip
Represents a trip planned by the user, including details about the destination, dates, and associated activities.

#### Fields:
- **id**: Unique identifier for the trip.
- **userId**: Foreign key linking the trip to the user who created it.
- **destinationId**: Foreign key linking to the trip's destination.
- **startDate**: The starting date of the trip.
- **endDate**: The ending date of the trip.
- **activities**: List of activities planned as part of the trip.

### 3. Destination
Represents a location where trips can be planned. Includes information about the name, description, and geographical details of the destination.

#### Fields:
- **id**: Unique identifier for the destination.
- **name**: Name of the destination.
- **description**: Description of the destination.
- **latitude**: Latitude coordinate of the destination.
- **longitude**: Longitude coordinate of the destination.

### 4. Booking
Represents a booking made for a trip, including the status of the booking and details about the trip and user.

#### Fields:
- **id**: Unique identifier for the booking.
- **userId**: Foreign key linking the booking to the user.
- **tripId**: Foreign key linking the booking to the trip.
- **status**: Status of the booking (e.g., confirmed, pending, canceled).
- **bookingDate**: Date the booking was made.

### 5. Activity
Represents an activity planned as part of a trip, including its name, description, schedule, and location.

#### Fields:
- **id**: Unique identifier for the activity.
- **tripId**: Foreign key linking the activity to the trip.
- **name**: Name of the activity.
- **description**: Description of the activity.
- **startTime**: Start time of the activity.
- **endTime**: End time of the activity.
- **location**: Location where the activity takes place.

## Features
- **JWT Authentication**: Ensures secure authentication using JSON Web Tokens.
- **Spring Security 6**: Provides security for API endpoints, ensuring proper authorization and access control.
- **CRUD Operations**: Supports full Create, Read, Update, and Delete operations for managing users, trips, destinations, bookings, and activities.
- **Gemini AI Integration**: Utilizes AI to provide enhanced trip planning and recommendation features based on user preferences.
- **PostgreSQL Database**: Handles data storage and management using a relational database system.

## Getting Started

### Prerequisites
- **Java 17+**: Ensure that you have Java Development Kit (JDK) 17 or higher installed.
- **PostgreSQL**: A running instance of PostgreSQL for database management.

### Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-username/ai-trip-planner-backend.git
    cd ai-trip-planner-backend
    ```

2. **Configure the Database**:
   - Update the `application.properties` or `application.yml` file with your PostgreSQL credentials.
   - Example (in `application.properties`):
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Build and Run the Application**:
    - Use Maven to build and run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the Application**:
   The API will be running at `http://localhost:8080`.

## Contributing
Contributions are welcome! Feel free to submit issues, feature requests, and pull requests.

### Steps for Contributing:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push them to your fork.
4. Open a pull request to merge your changes into the main repository.

## Contact
For any questions or suggestions, please contact:

- **Author**: Hassan Jamshaid
- **Email**: your-email@example.com

