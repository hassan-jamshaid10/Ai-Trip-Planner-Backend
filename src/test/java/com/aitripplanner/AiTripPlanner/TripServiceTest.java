package com.aitripplanner.AiTripPlanner;


import com.aitripplanner.AiTripPlanner.Entites.Trip;
import com.aitripplanner.AiTripPlanner.Repository.TripRepository;
import com.aitripplanner.AiTripPlanner.Services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripServiceTest {

    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    private Trip trip;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trip = new Trip(); // Customize as necessary.
        trip.setId(1);
    }

    @Test
    void createTrip_ShouldSaveAndReturnTrip() {
        when(tripRepository.save(trip)).thenReturn(trip);

        Trip createdTrip = tripService.createTrip(trip);

        assertNotNull(createdTrip);
        assertEquals(trip, createdTrip);
    }

    @Test
    void getTripById_ShouldReturnTrip_WhenExists() {
        when(tripRepository.findById(1)).thenReturn(Optional.of(trip));

        Optional<Trip> result = tripService.getTripById(1);

        assertTrue(result.isPresent());
        assertEquals(trip, result.get());
    }

    @Test
    void getTripById_ShouldReturnEmpty_WhenNotExists() {
        when(tripRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Trip> result = tripService.getTripById(1);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllTrips_ShouldReturnListOfTrips() {
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        when(tripRepository.findAll()).thenReturn(trips);

        List<Trip> result = tripService.getAllTrips();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void updateTrip_ShouldUpdateAndReturnTrip_WhenExists() {
        when(tripRepository.existsById(1)).thenReturn(true);
        when(tripRepository.save(trip)).thenReturn(trip);

        Trip updatedTrip = tripService.updateTrip(1, trip);

        assertNotNull(updatedTrip);
        assertEquals(trip, updatedTrip);
    }

    @Test
    void updateTrip_ShouldReturnNull_WhenNotExists() {
        when(tripRepository.existsById(1)).thenReturn(false);

        Trip updatedTrip = tripService.updateTrip(1, trip);

        assertNull(updatedTrip);
    }

    @Test
    void deleteTrip_ShouldDeleteTrip() {
        tripService.deleteTrip(1);

        verify(tripRepository, times(1)).deleteById(1);
    }
}
