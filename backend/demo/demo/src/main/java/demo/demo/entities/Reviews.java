package demo.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "_id")
    private UUID id;

    @Column(name="gig_id")
    private Long gigId;

    @Column(name = "user_id")
    private UUID userId;

    private Integer rating;

    private String comment;

    private Instant created_at =Instant.now();


}
