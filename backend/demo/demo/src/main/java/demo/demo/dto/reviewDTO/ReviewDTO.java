package demo.demo.dto.reviewDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private UUID _id;
    private Long gigId;
    private UUID userId;
    private Integer star;
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer __v;
}
