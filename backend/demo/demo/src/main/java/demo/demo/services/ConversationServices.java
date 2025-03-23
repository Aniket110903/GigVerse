package demo.demo.services;

import demo.demo.dto.conversationDTO.ConversationRequest;
import demo.demo.entities.Conversation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ConversationServices {
    public abstract Conversation createConversation(UUID userId, boolean isSeller, ConversationRequest request);
    public abstract Conversation updateConversation(UUID userId, boolean isSeller);
    public abstract  Conversation getSingleConversation(UUID userId);
    public abstract List<Conversation> getConversations(String userId, boolean isSeller);
}
