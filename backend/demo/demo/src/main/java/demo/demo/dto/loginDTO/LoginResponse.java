package demo.demo.dto.loginDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UUID _id;
    private String fullname;
    private String email;
    private String img;
    private String expertise;
    private String desc;
    private Boolean isSeller;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer __v;
}
