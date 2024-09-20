package com.aitripplanner.AiTripPlanner.Controller;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import com.aitripplanner.AiTripPlanner.Services.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendations")
public class BookingRecommendationController {

    private final BookingRecommendationService bookingRecommendationService;
    private final GeminiService geminiService; // Inject GeminiService

    public BookingRecommendationController(BookingRecommendationService bookingRecommendationService, GeminiService geminiService) {
        this.bookingRecommendationService = bookingRecommendationService;
        this.geminiService = geminiService;
    }

    // Retrieve all recommendations
    @GetMapping
    public List<BookingRecommendation> getAllRecommendations() {
        return bookingRecommendationService.getAllRecommendations();
    }

    // Retrieve recommendation by id
    @GetMapping("/{id}")
    public Optional<BookingRecommendation> getRecommendationById(@PathVariable Integer id) {
        return bookingRecommendationService.getRecommendationById(id);
    }

    // Save a new recommendation
    @PostMapping
    public BookingRecommendation saveRecommendation(@RequestBody BookingRecommendation recommendation) {
        return bookingRecommendationService.saveRecommendation(recommendation);
    }

    // Delete a recommendation by id
    @DeleteMapping("/{id}")
    public void deleteRecommendation(@PathVariable Integer id) {
        bookingRecommendationService.deleteRecommendation(id);
    }

    // Get AI recommendations based on trip and booking data
    @PostMapping("/ai")
    public List<BookingRecommendation> getAIRecommendations(@RequestBody RecommendationRequest request) {
        return geminiService.getRecommendations(request.getTrip(), request.getBooking());
    }

    // Inner class to handle incoming request payload
    public static class RecommendationRequest {
        private Trip trip;
        private Booking booking;

        public Trip getTrip() {
            return trip;
        }

        public void setTrip(Trip trip) {
            this.trip = trip;
        }

        public Booking getBooking() {
            return booking;
        }

        public void setBooking(Booking booking) {
            this.booking = booking;
        }
    }
}
