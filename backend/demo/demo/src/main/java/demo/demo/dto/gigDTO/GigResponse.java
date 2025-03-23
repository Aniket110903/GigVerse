package demo.demo.dto.gigDTO;

import lombok.*;

import java.time.Instant;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GigResponse {
    private String id;
    private String userId;
    private String title;
    private String desc;
    private int totalStar;
    private int starNumber;
    private String category;
    private Double price;
    private String cover;
    private List<String> images;
    private String shortTitle;
    private String shortDesc;
    private int deliveryTime;
    private int revision;
    private List<String> features;
    private int sales;
    private Instant createdAt;
    private Instant updatedAt;


}
