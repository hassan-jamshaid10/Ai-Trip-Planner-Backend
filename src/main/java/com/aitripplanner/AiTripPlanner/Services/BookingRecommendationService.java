package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Repository.BookingRecommendationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingRecommendationService {

    private final  BookingRecommendationRepository bookingRecommendationRepository;

    public BookingRecommendationService(BookingRecommendationRepository bookingRecommendationRepository) {
        this.bookingRecommendationRepository = bookingRecommendationRepository;
    }

    public List<BookingRecommendation> getAllRecommendations() {
        return bookingRecommendationRepository.findAll();
    }

    public Optional<BookingRecommendation> getRecommendationById(Integer id) {
        return bookingRecommendationRepository.findById(id);
    }

    public BookingRecommendation saveRecommendation(BookingRecommendation recommendation) {
        return bookingRecommendationRepository.save(recommendation);
    }

    public void deleteRecommendation(Integer id) {
        bookingRecommendationRepository.deleteById(id);
    }
}
