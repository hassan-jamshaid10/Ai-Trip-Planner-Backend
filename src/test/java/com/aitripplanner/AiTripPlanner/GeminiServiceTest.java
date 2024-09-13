package com.aitripplanner.AiTripPlanner;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Services.GeminiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeminiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeminiService geminiService;

    @Test
    void getRecommendations_ShouldReturnRecommendations() {
        // Arrange
        Trip trip = new Trip(); // Set up your Trip object as needed
        Booking booking = new Booking(); // Set up your Booking object as needed

        BookingRecommendation[] recommendationsArray = {
                new BookingRecommendation(), // Set up your BookingRecommendation object as needed
                new BookingRecommendation()
        };

        ResponseEntity<BookingRecommendation[]> response = ResponseEntity.ok(recommendationsArray);

        // Mock the RestTemplate call
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(response);

        // Act
        List<BookingRecommendation> recommendations = geminiService.getRecommendations(trip, booking);

        // Assert
        assertEquals(2, recommendations.size());
        // Additional assertions as needed
    }
}
