package demo.demo.serviceImplementation;

import demo.demo.dto.messageDTO.MessageDto;
import demo.demo.entities.Conversation;
import demo.demo.entities.Message;
import demo.demo.entities.Users;
import demo.demo.repository.ConversationRepository;
import demo.demo.repository.MessageRepository;
import demo.demo.repository.UserRepository;
import demo.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImplementation extends MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    public Message createMessage(UUID userId, boolean isSeller, UUID conversationId, String description) {
        Message message = new Message();
        message.setUserId(userId);
        message.setConversationId(conversationId);
        message.setDescription(description);

        Message savedMessage = messageRepository.save(message);

        // Update conversation last message
        Optional<Conversation> convoOpt = conversationRepository.findById(conversationId);
        convoOpt.ifPresent(convo -> {
            convo.setLastMessage(description);
            convo.setReadBySeller(isSeller);
            convo.setReadByBuyer(!isSeller);
            conversationRepository.save(convo);
        });

        return savedMessage;
    }

    public List<MessageDto> getMessages(UUID conversationId) {
        List<Message> messages = messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);
        return messages.stream().map(message -> {
            Optional<Users> user = userRepository.findById(message.getUserId());
            return new MessageDto(
                    message.getId(),
                    message.getConversationId(),
                    message.getUserId(),
                    message.getDescription(),
                    message.getCreatedAt(),
                    user.map(Users::getImg).orElse(null)
            );
        }).collect(Collectors.toList());
    }
}
