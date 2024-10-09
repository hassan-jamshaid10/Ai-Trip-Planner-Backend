package com.aitripplanner.AiTripPlanner.Entites;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    @Lob
    private String description;

    @Lob
    private String attractions;  // Could be JSON or text

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Activity> activities;

}
