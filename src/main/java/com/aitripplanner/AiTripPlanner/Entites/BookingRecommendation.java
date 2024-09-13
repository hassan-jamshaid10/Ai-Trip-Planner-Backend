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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "recommended_bookings", length = 500)
    private String recommendedBookings;  // Store AI-generated booking details

    @Column(name = "generated_date")
    private LocalDate generatedDate;  // Date when recommendations were made
}
