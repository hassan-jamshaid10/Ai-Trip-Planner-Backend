package com.aitripplanner.AiTripPlanner.Controller;


import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking-recommendations")
public class BookingRecommendationController {

    @Autowired
    private BookingRecommendationService bookingRecommendationService;

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<BookingRecommendation>> getRecommendationsForTrip(@PathVariable Integer tripId) {
        List<BookingRecommendation> recommendations = bookingRecommendationService.getRecommendationsForTrip(tripId);
        return ResponseEntity.ok(recommendations);
    }
}
