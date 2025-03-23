package demo.demo.dto.loginDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPayload {
    private String fullName;
    private String email;
    private String img;
    private String expertise;
    private String desc;
    private Boolean isSeller;
    private String createdAt;
    private String updatedAt;
    private Integer __v;
    private String password;
}
