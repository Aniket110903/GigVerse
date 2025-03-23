package demo.demo.services;

import demo.demo.dto.messageDTO.MessageDto;
import demo.demo.entities.Message;

import java.util.List;
import java.util.UUID;

public abstract class MessageService {
    public abstract Message createMessage(UUID userId, boolean isSeller,UUID convoId,String desc);
    public abstract List<MessageDto> getMessages(UUID id);
}
