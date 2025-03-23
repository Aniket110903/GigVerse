package demo.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "gig_id")
    private Long gigId;

    @Column(name = "img")
    private String img;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "buyer_id")
    private UUID buyerId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "is_completed")
    private Boolean isCompleted = true;

    @Column(name = "payment_intent")
    private String paymentIntent;

}

