package demo.demo.dto.orderDTO;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID id;
    private Long gigId;
    private String img;
    private String title;
    private Double price;
    private String sellerId;
    private String buyerId;
    private String buyerName;
    private String sellerName;
    private Boolean isCompleted;
    private String paymentIntent;
}
