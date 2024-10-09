package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Controller.DestinationController;
import com.aitripplanner.AiTripPlanner.Entites.Destination;
import com.aitripplanner.AiTripPlanner.Services.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DestinationControllerTest {

    @Mock
    private DestinationService destinationService;

    @InjectMocks
    private DestinationController destinationController;

    private Destination destination;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        destination = new Destination();
        destination.setId(1);
        destination.setName("Paris");
    }

    @Test
    void createDestination_ShouldReturnCreatedDestination() {
        when(destinationService.createDestination(any(Destination.class))).thenReturn(destination);

        ResponseEntity<Destination> response = destinationController.createDestination(destination);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(destinationService, times(1)).createDestination(any(Destination.class));
    }

    @Test
    void getDestinationById_ShouldReturnDestinationIfFound() {
        when(destinationService.getDestinationById(1)).thenReturn(Optional.of(destination));

        ResponseEntity<Destination> response = destinationController.getDestinationById(1);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destination.getId(), response.getBody().getId());
    }

    @Test
    void getDestinationById_ShouldReturnNotFoundIfDestinationDoesNotExist() {
        when(destinationService.getDestinationById(1)).thenReturn(Optional.empty());

        ResponseEntity<Destination> response = destinationController.getDestinationById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllDestinations_ShouldReturnListOfDestinations() {
        List<Destination> destinations = Arrays.asList(destination, new Destination());
        when(destinationService.getAllDestinations()).thenReturn(destinations);

        ResponseEntity<List<Destination>> response = destinationController.getAllDestinations();

        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateDestination_ShouldReturnUpdatedDestinationIfFound() {
        when(destinationService.updateDestination(anyInt(), any(Destination.class))).thenReturn(destination);

        ResponseEntity<Destination> response = destinationController.updateDestination(1, destination);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destination.getId(), response.getBody().getId());
    }

    @Test
    void updateDestination_ShouldReturnNotFoundIfDestinationDoesNotExist() {
        when(destinationService.updateDestination(anyInt(), any(Destination.class))).thenReturn(null);

        ResponseEntity<Destination> response = destinationController.updateDestination(1, destination);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteDestination_ShouldReturnNoContentIfDeleted() {
        ResponseEntity<Void> response = destinationController.deleteDestination(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(destinationService, times(1)).deleteDestination(1);
    }
}
