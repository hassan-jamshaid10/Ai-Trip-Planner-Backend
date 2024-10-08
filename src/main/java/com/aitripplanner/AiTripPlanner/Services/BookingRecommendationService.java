package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Repository.BookingRecommendationRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingRecommendationService {

    private final BookingRecommendationRepository bookingRecommendationRepository;

    public BookingRecommendationService(BookingRecommendationRepository bookingRecommendationRepository) {
        this.bookingRecommendationRepository = bookingRecommendationRepository;
    }

    // Simple create function
    public BookingRecommendation createRecommendation(BookingRecommendation recommendation) {
        return bookingRecommendationRepository.save(recommendation);
    }

    // Simple delete function
    public boolean deleteRecommendation(Integer id) {
        if (bookingRecommendationRepository.existsById(id)) {
            bookingRecommendationRepository.deleteById(id);
            return true; // Return true after successful deletion
        } else {
            throw new IllegalArgumentException("Recommendation with id " + id + " does not exist.");
        }
    }
}
