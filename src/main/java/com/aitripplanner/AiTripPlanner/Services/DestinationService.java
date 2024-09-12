package com.aitripplanner.AiTripPlanner.Services;
import com.aitripplanner.AiTripPlanner.Entites.Destination;
import com.aitripplanner.AiTripPlanner.Repository.DestinationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public Optional<Destination> getDestinationById(Integer id) {
        return destinationRepository.findById(id);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination updateDestination(Integer id, Destination destination) {
        if (destinationRepository.existsById(id)) {
            destination.setId(id);
            return destinationRepository.save(destination);
        }
        return null;
    }

    public void deleteDestination(Integer id) {
        destinationRepository.deleteById(id);
    }
}
