package com.aitripplanner.AiTripPlanner.Repository;


import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRecommendationRepository extends JpaRepository<BookingRecommendation, Integer> {
    // Additional query methods (if needed) can be defined here
}
