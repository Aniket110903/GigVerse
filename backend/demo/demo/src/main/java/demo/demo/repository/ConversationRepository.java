package demo.demo.repository;

import demo.demo.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.*;

@RepositoryRestResource
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
    List<Conversation> findBySellerIdOrderByUpdatedAtDesc(UUID sellerId);
    List<Conversation> findByBuyerIdOrderByUpdatedAtDesc(UUID buyerId);
    Optional<Conversation> findById(UUID id);
}
