package com.aitripplanner.AiTripPlanner.Entites;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_recommendations")
public class BookingRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading for performance
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading for performance
    @JoinColumn(name = "trip_id", nullable = false) // Foreign key to Trip
    private Trip trip;

    @Column(name = "recommended_bookings", length = 500)
    private String recommendedBookings; // Store AI-generated booking details

    @Column(name = "generated_date", nullable = false) // Ensure generated date is always set
    private LocalDate generatedDate; // Date when recommendations were made

    // Optional: Override toString() for better readability in logs
    @Override
    public String toString() {
        return "BookingRecommendation{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) + // Avoid potential NPE
                ", trip=" + (trip != null ? trip.getId() : null) + // Avoid potential NPE
                ", recommendedBookings='" + recommendedBookings + '\'' +
                ", generatedDate=" + generatedDate +
                '}';
    }

}
