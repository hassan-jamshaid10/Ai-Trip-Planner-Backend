package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Repository.BookingRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingRecommendationService {

    @Autowired
    private BookingRecommendationRepository bookingRecommendationRepository;

    @Autowired
    private GeminiService geminiService;

    public List<BookingRecommendation> getRecommendationsForTrip(Integer tripId) {
        // Get the trip details from TripService or repository
        // Assuming you have a method to get Trip by ID
        Trip trip = getTripById(tripId);  // You should implement this method
        // Get booking details as needed
        Booking booking = getBookingForTrip(tripId);  // You should implement this method

        // Call GeminiService to get recommendations
        List<BookingRecommendation> recommendations = geminiService.getRecommendations(trip, booking);

        // Optionally save recommendations to the database
        bookingRecommendationRepository.saveAll(recommendations);
        return recommendations;
    }

    // Stub methods for demonstration; implement these methods as needed
    private Trip getTripById(Integer tripId) {
        // Retrieve Trip from TripService or repository
        return new Trip();  // Replace with actual implementation
    }

    private Booking getBookingForTrip(Integer tripId) {
        // Retrieve Booking related to the Trip
        return new Booking();  // Replace with actual implementation
    }
}
