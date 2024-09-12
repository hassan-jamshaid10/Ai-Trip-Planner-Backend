package com.aitripplanner.AiTripPlanner.Repository;

import com.aitripplanner.AiTripPlanner.Entites.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
