package com.aitripplanner.AiTripPlanner.Services;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Create a new booking.
     *
     * @param booking The booking object to be created.
     * @return The saved booking entity.
     */
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Retrieve a booking by its ID.
     *
     * @param id The ID of the booking.
     * @return An Optional containing the booking if found, otherwise empty.
     */
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    /**
     * Retrieve all bookings.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Update an existing booking.
     *
     * @param id The ID of the booking to be updated.
     * @param booking The booking object with updated information.
     * @return The updated booking entity, or null if the booking does not exist.
     */
    public Booking updateBooking(Integer id, Booking booking) {
        if (bookingRepository.existsById(id)) {
            booking.setId(id);
            return bookingRepository.save(booking);
        }
        return null;
    }

    /**
     * Delete a booking by its ID.
     *
     * @param id The ID of the booking to be deleted.
     */
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }

    /**
     * Retrieve a list of bookings for a given trip ID.
     *
     * @param tripId The ID of the trip whose bookings need to be fetched.
     * @return A list of bookings associated with the specified trip.
     */
    public List<Booking> getBookingsForTrip(Integer tripId) {
        return bookingRepository.findByTripId(tripId);
    }
}
