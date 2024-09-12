AI Trip Planner Backend
Overview
This project is a backend service for an AI-powered trip planner application, built with Spring Boot, Spring Security 6, and JWT authentication. It provides a robust and secure API for managing users, trips, destinations, bookings, and activities.

Technologies
Spring Boot: Framework for building the backend service.
Spring Security 6: Provides security and authentication features.
JWT Authentication: Secure authentication using JSON Web Tokens.
Gemini AI: AI technology for enhancing trip planning capabilities.
PostgreSQL: Relational database management system used for storing data.
Entities
User
Represents a user of the trip planner application. Contains information such as username, email, and hashed password. Users can create and manage trips, make bookings, and interact with various activities.

Fields:

id: Unique identifier for the user.
username: Username for the user.
email: Email address of the user.
passwordHash: Hashed password for secure authentication.
preferences: User preferences related to trips and destinations.
trips: List of trips associated with the user.
Trip
Represents a trip planned by the user. Includes details about the trip's destination, dates, and associated activities.

Fields:

id: Unique identifier for the trip.
userId: Foreign key linking to the user who created the trip.
destinationId: Foreign key linking to the destination of the trip.
startDate: Starting date of the trip.
endDate: Ending date of the trip.
activities: List of activities planned for the trip.
Destination
Represents a location where trips can be planned. Includes information about the location's name, description, and geographical details.

Fields:

id: Unique identifier for the destination.
name: Name of the destination.
description: Description of the destination.
latitude: Latitude coordinate of the destination.
longitude: Longitude coordinate of the destination.
Booking
Represents a booking made for a trip. Includes details about the booking status, trip, and associated user.

Fields:

id: Unique identifier for the booking.
userId: Foreign key linking to the user who made the booking.
tripId: Foreign key linking to the trip being booked.
status: Status of the booking (e.g., confirmed, pending, canceled).
bookingDate: Date when the booking was made.
Activity
Represents an activity planned as part of a trip. Includes details about the activity, its schedule, and location.

Fields:

id: Unique identifier for the activity.
tripId: Foreign key linking to the trip which includes the activity.
name: Name of the activity.
description: Description of the activity.
startTime: Start time of the activity.
endTime: End time of the activity.
location: Location where the activity takes place.
Features
JWT Authentication: Secure authentication with JSON Web Tokens.
Spring Security: Robust security configuration for API endpoints.
CRUD Operations: Create, read, update, and delete operations for managing users, trips, destinations, bookings, and activities.
Gemini AI: Utilized for advanced trip planning features.
PostgreSQL: Used for data storage and management.
Getting Started
Clone the repository:

bash
git clone https://github.com/your-username/ai-trip-planner-backend.git

Navigate to the project directory:

bash
cd ai-trip-planner-backend

Build and run the application:

bash
./mvnw spring-boot:run


Contributing
Feel free to submit issues and pull requests. Contributions are welcome!

License
This project is licensed under the MIT License. See the LICENSE file for details.
