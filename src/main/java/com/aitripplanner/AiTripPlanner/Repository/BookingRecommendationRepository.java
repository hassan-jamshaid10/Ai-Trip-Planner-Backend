package com.aitripplanner.AiTripPlanner.Repository;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRecommendationRepository extends JpaRepository<BookingRecommendation, Integer> {
    // Method to find recommendations by trip ID
    List<BookingRecommendation> findByTripId(Integer tripId);
}
