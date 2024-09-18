package com.aitripplanner.AiTripPlanner.Repository;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Method to find bookings by Trip ID
    List<Booking> findByTripId(Integer tripId);
}
