package com.aitripplanner.AiTripPlanner.Controller;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
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
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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

    // Create a Booking Recommendation for a Trip
    @PostMapping("/{tripId}/recommendations")
    public ResponseEntity<BookingRecommendation> createRecommendation(@PathVariable Integer tripId,
                                                                      @RequestBody BookingRecommendation recommendation) {
        // Fetch the Trip object using tripId
        Optional<Trip> tripOptional = tripService.getTripById(tripId);
        if (tripOptional.isPresent()) {
            recommendation.setTrip(tripOptional.get()); // Set the Trip object in the BookingRecommendation
            BookingRecommendation createdRecommendation = bookingRecommendationService.createRecommendation(recommendation);
            return new ResponseEntity<>(createdRecommendation, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if Trip not found
        }
    }

    // Delete a Booking Recommendation by ID
    @DeleteMapping("/{tripId}/recommendations/{recommendationId}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Integer tripId, @PathVariable Integer recommendationId) {
        boolean isDeleted = bookingRecommendationService.deleteRecommendation(recommendationId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if deletion was successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
        }
    }
}
