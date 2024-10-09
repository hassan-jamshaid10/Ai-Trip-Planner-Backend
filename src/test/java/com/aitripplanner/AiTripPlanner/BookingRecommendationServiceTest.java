package com.aitripplanner.AiTripPlanner;


import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Repository.BookingRecommendationRepository;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingRecommendationServiceTest {

    @Mock
    private BookingRecommendationRepository bookingRecommendationRepository;

    @InjectMocks
    private BookingRecommendationService bookingRecommendationService;

    public BookingRecommendationServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRecommendation() {
        BookingRecommendation recommendation = new BookingRecommendation();
        when(bookingRecommendationRepository.save(recommendation)).thenReturn(recommendation);

        BookingRecommendation createdRecommendation = bookingRecommendationService.createRecommendation(recommendation);
        assertEquals(recommendation, createdRecommendation);
        verify(bookingRecommendationRepository, times(1)).save(recommendation);
    }

    @Test
    public void testDeleteRecommendation() {
        when(bookingRecommendationRepository.existsById(1)).thenReturn(true);
        doNothing().when(bookingRecommendationRepository).deleteById(1);

        boolean isDeleted = bookingRecommendationService.deleteRecommendation(1);
        assertTrue(isDeleted);
        verify(bookingRecommendationRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteRecommendationNotFound() {
        when(bookingRecommendationRepository.existsById(1)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookingRecommendationService.deleteRecommendation(1);
        });
        assertEquals("Recommendation with id 1 does not exist.", exception.getMessage());
        verify(bookingRecommendationRepository, times(0)).deleteById(1);
    }
}
