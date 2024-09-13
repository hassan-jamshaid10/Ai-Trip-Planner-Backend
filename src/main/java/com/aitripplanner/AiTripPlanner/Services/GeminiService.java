package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
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

    private final RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String googleApiKey;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookingRecommendation> getRecommendations(Trip trip, Booking booking) {
        // Prepare the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + googleApiKey);
        headers.set("Content-Type", "application/json");

        // Prepare the request body
        GeminiRequestPayload payload = new GeminiRequestPayload(trip, booking);
        HttpEntity<GeminiRequestPayload> request = new HttpEntity<>(payload, headers);

        // Build the API URL with API key
        String url = UriComponentsBuilder.fromHttpUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                .queryParam("key", googleApiKey)
                .toUriString();

        // Call the Gemini API using RestTemplate
        ResponseEntity<BookingRecommendation[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                BookingRecommendation[].class
        );

        // Convert the response body to a list and return it
        BookingRecommendation[] recommendationsArray = response.getBody();
        return recommendationsArray != null ? List.of(recommendationsArray) : List.of();
    }

    // Inner class to represent the payload
    private static class GeminiRequestPayload {
        private Trip trip;
        private Booking booking;

        public GeminiRequestPayload(Trip trip, Booking booking) {
            this.trip = trip;
            this.booking = booking;
        }

        // Getters and setters if necessary
    }
}
