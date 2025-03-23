package demo.demo.serviceImplementation;

import demo.demo.dto.conversationDTO.ConversationRequest;
import demo.demo.entities.Conversation;
import demo.demo.entities.Users;
import demo.demo.repository.ConversationRepository;
import demo.demo.repository.UserRepository;
import demo.demo.services.ConversationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConversationServiceImplementation extends ConversationServices {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    public Conversation createConversation(UUID userId, boolean isSeller, ConversationRequest request) {
        Conversation conversation = new Conversation();

        // Generate ID based on the buyer-seller combination
        String generatedId = isSeller
                ? userId.toString() + request.getTo().toString()
                : request.getTo().toString() + userId.toString();

        conversation.setId(UUID.nameUUIDFromBytes(generatedId.getBytes()));
        conversation.setSellerId(isSeller ? userId : request.getTo());
        conversation.setBuyerId(isSeller ? request.getTo() : userId);
        conversation.setBuyerName(request.getBuyerName());
        conversation.setSellerName(request.getSellerName());
        conversation.setReadBySeller(isSeller);
        conversation.setReadByBuyer(!isSeller);

        return conversationRepository.save(conversation);
    }

    public List<Conversation> getConversations(String userId, boolean isSeller) {
        List<Conversation> conversations = isSeller
                ? conversationRepository.findBySellerIdOrderByUpdatedAtDesc(UUID.fromString(userId))
                : conversationRepository.findByBuyerIdOrderByUpdatedAtDesc(UUID.fromString(userId));

        return conversations.stream().map(convo -> {
            Optional<Users> seller = userRepository.findById(convo.getSellerId());
            Optional<Users> buyer = userRepository.findById(convo.getBuyerId());

            seller.ifPresent(user -> convo.setSellerName(user.getFullName()));
            buyer.ifPresent(user -> convo.setBuyerName(user.getFullName()));

            return convo;
        }).collect(Collectors.toList());
    }

    public Conversation updateConversation(UUID conversationId, boolean isSeller) {
        Optional<Conversation> convoOpt = conversationRepository.findById(conversationId);
        if (convoOpt.isPresent()) {
            Conversation conversation = convoOpt.get();
            if (isSeller) {
                conversation.setReadBySeller(true);
            } else {
                conversation.setReadByBuyer(true);
            }
            return conversationRepository.save(conversation);
        }
        return null;
    }

    public Conversation getSingleConversation(UUID conversationId) {
        Optional<Conversation> data = conversationRepository.findById(conversationId);
        Conversation res = new Conversation();
        if(data.isPresent()){
            res = data.get();
        }
        return res;
    }
}
