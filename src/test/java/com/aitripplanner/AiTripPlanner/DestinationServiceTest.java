package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Entites.Destination;
import com.aitripplanner.AiTripPlanner.Repository.DestinationRepository;
import com.aitripplanner.AiTripPlanner.Services.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DestinationServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;

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
        when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        Destination createdDestination = destinationService.createDestination(destination);

        assertNotNull(createdDestination);
        assertEquals(destination.getId(), createdDestination.getId());
        verify(destinationRepository, times(1)).save(any(Destination.class));
    }

    @Test
    void getDestinationById_ShouldReturnDestinationIfFound() {
        when(destinationRepository.findById(1)).thenReturn(Optional.of(destination));

        Optional<Destination> foundDestination = destinationService.getDestinationById(1);

        assertTrue(foundDestination.isPresent());
        assertEquals(destination.getId(), foundDestination.get().getId());
    }

    @Test
    void getAllDestinations_ShouldReturnListOfDestinations() {
        List<Destination> destinationList = Arrays.asList(destination, new Destination());
        when(destinationRepository.findAll()).thenReturn(destinationList);

        List<Destination> result = destinationService.getAllDestinations();

        assertEquals(2, result.size());
    }

    @Test
    void updateDestination_ShouldReturnUpdatedDestinationIfFound() {
        when(destinationRepository.existsById(1)).thenReturn(true);
        when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        Destination updatedDestination = destinationService.updateDestination(1, destination);

        assertNotNull(updatedDestination);
        assertEquals(destination.getId(), updatedDestination.getId());
    }

    @Test
    void updateDestination_ShouldReturnNullIfNotFound() {
        when(destinationRepository.existsById(1)).thenReturn(false);

        Destination result = destinationService.updateDestination(1, destination);

        assertNull(result);
    }

    @Test
    void deleteDestination_ShouldDeleteDestinationIfExists() {
        destinationService.deleteDestination(1);

        verify(destinationRepository, times(1)).deleteById(1);
    }
}
