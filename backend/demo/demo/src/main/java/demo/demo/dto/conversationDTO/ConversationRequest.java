package demo.demo.dto.conversationDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConversationRequest {
    private UUID to;
    private String buyerName;
    private String sellerName;
}
