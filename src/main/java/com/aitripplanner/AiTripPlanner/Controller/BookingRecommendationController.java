package com.aitripplanner.AiTripPlanner.Controller;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class BookingRecommendationController {

    private final BookingRecommendationService bookingRecommendationService;

    public BookingRecommendationController(BookingRecommendationService bookingRecommendationService) {
        this.bookingRecommendationService = bookingRecommendationService;
    }

    // Save a new recommendation
    @PostMapping
    public ResponseEntity<BookingRecommendation> saveRecommendation(@RequestBody BookingRecommendation recommendation) {
        BookingRecommendation createdRecommendation = bookingRecommendationService.createRecommendation(recommendation);
        return new ResponseEntity<>(createdRecommendation, HttpStatus.CREATED); // Return 201 status
    }

    // Delete a recommendation by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Integer id) {
        boolean isDeleted = bookingRecommendationService.deleteRecommendation(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if deletion was successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
        }
    }
}
