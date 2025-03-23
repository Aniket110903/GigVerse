package demo.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gigs")
public class Gig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    private String title;
    private String category;
    private String cover;
    private String description;
    private int totalStar;
    private int starNumber;
    private int sales;

    @Column(name = "short_title")
    private String shortTitle;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "delivery_time")
    private int deliveryTime;

    private int revision;
    private Double price;

    @Column(columnDefinition = "TEXT[]")
    private String[] images;  // PostgreSQL array

    @Column(columnDefinition = "TEXT[]")
    private String[] features;  // PostgreSQL array

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}
