package com.aitripplanner.AiTripPlanner;
import com.aitripplanner.AiTripPlanner.Controller.TripController;
import com.aitripplanner.AiTripPlanner.Entites.BookingRecommendation;
import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Services.BookingRecommendationService;
import com.aitripplanner.AiTripPlanner.Services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripControllerTest {

    @InjectMocks
    private TripController tripController;

    @Mock
    private TripService tripService;

    @Mock
    private BookingRecommendationService bookingRecommendationService;

    private Trip trip;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trip = new Trip(); // You can customize this with attributes if needed.
        trip.setId(1);
    }

    @Test
    void createTrip_ShouldReturnCreatedTrip() {
        when(tripService.createTrip(any(Trip.class))).thenReturn(trip);

        ResponseEntity<Trip> response = tripController.createTrip(trip);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(trip, response.getBody());
    }

    @Test
    void getTripById_ShouldReturnTrip_WhenExists() {
        when(tripService.getTripById(1)).thenReturn(Optional.of(trip));

        ResponseEntity<Trip> response = tripController.getTripById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(trip, response.getBody());
    }

    @Test
    void getTripById_ShouldReturnNotFound_WhenNotExists() {
        when(tripService.getTripById(1)).thenReturn(Optional.empty());

        ResponseEntity<Trip> response = tripController.getTripById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllTrips_ShouldReturnListOfTrips() {
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        when(tripService.getAllTrips()).thenReturn(trips);

        ResponseEntity<List<Trip>> response = tripController.getAllTrips();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void updateTrip_ShouldReturnUpdatedTrip_WhenTripExists() {
        when(tripService.updateTrip(eq(1), any(Trip.class))).thenReturn(trip);

        ResponseEntity<Trip> response = tripController.updateTrip(1, trip);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(trip, response.getBody());
    }

    @Test
    void updateTrip_ShouldReturnNotFound_WhenTripNotExists() {
        when(tripService.updateTrip(eq(1), any(Trip.class))).thenReturn(null);

        ResponseEntity<Trip> response = tripController.updateTrip(1, trip);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteTrip_ShouldReturnNoContent() {
        ResponseEntity<Void> response = tripController.deleteTrip(1);

        verify(tripService, times(1)).deleteTrip(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void createRecommendation_ShouldReturnCreatedRecommendation_WhenTripExists() {
        BookingRecommendation recommendation = new BookingRecommendation();
        when(tripService.getTripById(1)).thenReturn(Optional.of(trip));
        when(bookingRecommendationService.createRecommendation(recommendation)).thenReturn(recommendation);

        ResponseEntity<BookingRecommendation> response = tripController.createRecommendation(1, recommendation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recommendation, response.getBody());
    }

    @Test
    void createRecommendation_ShouldReturnNotFound_WhenTripNotExists() {
        BookingRecommendation recommendation = new BookingRecommendation();
        when(tripService.getTripById(1)).thenReturn(Optional.empty());

        ResponseEntity<BookingRecommendation> response = tripController.createRecommendation(1, recommendation);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteRecommendation_ShouldReturnNoContent_WhenDeleted() {
        when(bookingRecommendationService.deleteRecommendation(1)).thenReturn(true);

        ResponseEntity<Void> response = tripController.deleteRecommendation(1, 1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteRecommendation_ShouldReturnNotFound_WhenNotDeleted() {
        when(bookingRecommendationService.deleteRecommendation(1)).thenReturn(false);

        ResponseEntity<Void> response = tripController.deleteRecommendation(1, 1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
