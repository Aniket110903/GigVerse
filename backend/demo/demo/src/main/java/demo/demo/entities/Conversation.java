package demo.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "conversation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "buyer_id", nullable = false)
    private UUID buyerId;

    @Column(name = "buyer_name", nullable = false)
    private String buyerName;

    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    @Column(name = "read_by_seller", nullable = false)
    private boolean readBySeller;

    @Column(name = "read_by_buyer", nullable = false)
    private boolean readByBuyer;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedAt = new java.util.Date();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new java.util.Date();
    }
}

