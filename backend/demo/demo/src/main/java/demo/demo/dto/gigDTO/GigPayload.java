package demo.demo.dto.gigDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GigPayload {
    private Long id;
    private String userId;

    private String title;

    private String category;

    private String cover;

    private List<String> images;

    private String desc;

    private String shortTitle;

    private String shortDesc;

    private Integer deliveryTime;

    private Integer revision;

    private List<String> features;

    private Double price;
}
