package com.aitripplanner.AiTripPlanner.Repository;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
