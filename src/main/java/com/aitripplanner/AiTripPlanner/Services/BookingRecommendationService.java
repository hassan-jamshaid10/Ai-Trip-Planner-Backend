package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Repository.BookingRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingRecommendationService {

    @Autowired
    private BookingRecommendationRepository bookingRecommendationRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private GeminiService geminiService;

    /**
     * Get booking recommendations for a given trip.
     *
     * @param tripId The ID of the trip for which recommendations are requested.
     * @return A list of booking recommendations.
     */
    public List<BookingRecommendation> getRecommendationsForTrip(Integer tripId) {
        // Retrieve the trip details
        Optional<Trip> tripOptional = tripService.getTripById(tripId);
        if (tripOptional.isEmpty()) {
            throw new IllegalArgumentException("Trip with ID " + tripId + " not found");
        }
        Trip trip = tripOptional.get();

        // Retrieve booking details for the trip
        List<Booking> bookings = bookingService.getBookingsForTrip(tripId);

        // Call GeminiService to get recommendations
        List<BookingRecommendation> recommendations = geminiService.getRecommendations(trip, (Booking) bookings);

        // Optionally save recommendations to the database
        bookingRecommendationRepository.saveAll(recommendations);

        return recommendations;
    }
}
