package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Controller.BookingRecommendationController;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
//test failed
public class BookingRecommendationControllerTest {

    @Mock
    private BookingRecommendationService bookingRecommendationService;

    @InjectMocks
    private BookingRecommendationController bookingRecommendationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRecommendation() {
        BookingRecommendation recommendation = new BookingRecommendation();
        when(bookingRecommendationService.createRecommendation(recommendation)).thenReturn(recommendation);

        ResponseEntity<BookingRecommendation> response = bookingRecommendationController.saveRecommendation(recommendation);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recommendation, response.getBody());
        verify(bookingRecommendationService, times(1)).createRecommendation(recommendation);
    }

    @Test
    public void testDeleteRecommendation() {
        when(bookingRecommendationService.deleteRecommendation(1)).thenReturn(true);

        ResponseEntity<Void> response = bookingRecommendationController.deleteRecommendation(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookingRecommendationService, times(1)).deleteRecommendation(1);
    }

    @Test
    public void testDeleteRecommendationNotFound() {
        when(bookingRecommendationService.deleteRecommendation(1)).thenThrow(new IllegalArgumentException("Recommendation with id 1 does not exist."));

        ResponseEntity<Void> response = bookingRecommendationController.deleteRecommendation(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookingRecommendationService, times(1)).deleteRecommendation(1);
    }
}
