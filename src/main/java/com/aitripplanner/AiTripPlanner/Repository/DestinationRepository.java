package com.aitripplanner.AiTripPlanner.Repository;

import com.aitripplanner.AiTripPlanner.Entites.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
