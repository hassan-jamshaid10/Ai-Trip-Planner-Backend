package com.aitripplanner.AiTripPlanner.Controller;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import com.aitripplanner.AiTripPlanner.Services.GeminiService;
import com.aitripplanner.AiTripPlanner.Services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private BookingRecommendationService bookingRecommendationService;

    @Autowired
    private GeminiService geminiService;

    // Create a new Trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripService.createTrip(trip);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    // Get a Trip by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer id) {
        Optional<Trip> trip = tripService.getTripById(id);
        return trip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    // Get all Trips
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    // Update a Trip by ID
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
        Trip updatedTrip = tripService.updateTrip(id, trip);
        return updatedTrip != null ? ResponseEntity.ok(updatedTrip)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete a Trip by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    // Get AI-based Booking Recommendations for a Trip
    @GetMapping("/{id}/recommendations")
    public ResponseEntity<?> getAIRecommendations(@PathVariable Integer id) {
        Optional<Trip> trip = tripService.getTripById(id);
        if (trip.isPresent()) {
            Trip existingTrip = trip.get();

            // Assuming the booking service or entity is providing current bookings for the trip
            List<Booking> bookings = existingTrip.getBookings(); // Modify this if you use a separate service for booking
            if (bookings.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found for this trip");
            }

            // Assuming the first booking is sent for AI recommendation (customize as needed)
            Booking booking = bookings.get(0);

            // Call the AI service for recommendations
            List<BookingRecommendation> recommendations = geminiService.getRecommendations(existingTrip, booking);
            return ResponseEntity.ok(recommendations);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
        }
    }
}
