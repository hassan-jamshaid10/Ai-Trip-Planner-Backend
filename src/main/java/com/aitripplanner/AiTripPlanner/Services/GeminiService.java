package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GeminiService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);

    private final RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String googleApiKey;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookingRecommendation> getRecommendations(Trip trip, Booking booking) {
        try {
            // Prepare request headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + googleApiKey);
            headers.set("Content-Type", "application/json");

            // Prepare request body
            GeminiRequestPayload payload = new GeminiRequestPayload(trip, booking);
            HttpEntity<GeminiRequestPayload> request = new HttpEntity<>(payload, headers);

            // API URL
            String url = UriComponentsBuilder
                    .fromHttpUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                    .queryParam("key", googleApiKey)
                    .toUriString();

            // Make API call
            ResponseEntity<BookingRecommendation[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    BookingRecommendation[].class
            );

            // Check for response body
            if (response.getBody() == null) {
                throw new RuntimeException("AI recommendation service returned no data.");
            }

            // Convert to list and return
            return List.of(response.getBody());

        } catch (Exception e) {
            logger.error("Failed to get AI recommendations: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to get AI recommendations: " + e.getMessage());
        }
    }

    // Inner class for request payload
    private static class GeminiRequestPayload {
        private Trip trip;
        private Booking booking;

        public GeminiRequestPayload(Trip trip, Booking booking) {
            this.trip = trip;
            this.booking = booking;
        }

        // Getters and setters (or use Lombok)
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
