package demo.demo.dto.messageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MessageDto {
    private UUID id;
    private UUID conversationId;
    private UUID userId;
    private String description;
    private Date createdAt;
    private String userImage;
}
