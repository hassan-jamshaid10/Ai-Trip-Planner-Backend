package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Entites.Booking;
import com.aitripplanner.AiTripPlanner.Repository.BookingRepository;
import com.aitripplanner.AiTripPlanner.Services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        booking = new Booking();
        booking.setId(1);
        // Set other necessary booking fields
    }

    @Test
    void testCreateBooking() {
        when(bookingRepository.save(booking)).thenReturn(booking);
        Booking savedBooking = bookingService.createBooking(booking);
        assertNotNull(savedBooking);
        assertEquals(booking.getId(), savedBooking.getId());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testGetBookingById() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        Optional<Booking> foundBooking = bookingService.getBookingById(1);
        assertTrue(foundBooking.isPresent());
        assertEquals(booking.getId(), foundBooking.get().getId());
        verify(bookingRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));
        List<Booking> bookings = bookingService.getAllBookings();
        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBooking() {
        when(bookingRepository.existsById(1)).thenReturn(true);
        when(bookingRepository.save(booking)).thenReturn(booking);
        Booking updatedBooking = bookingService.updateBooking(1, booking);
        assertNotNull(updatedBooking);
        verify(bookingRepository, times(1)).existsById(1);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testDeleteBooking() {
        bookingService.deleteBooking(1);
        verify(bookingRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetBookingsForTrip() {
        when(bookingRepository.findByTripId(1)).thenReturn(Arrays.asList(booking));
        List<Booking> bookings = bookingService.getBookingsForTrip(1);
        assertFalse(bookings.isEmpty());
        verify(bookingRepository, times(1)).findByTripId(1);
    }
}
