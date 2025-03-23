package demo.demo.repository;

import demo.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByConversationId( UUID conversationId);
    List<Message> findByConversationIdOrderByCreatedAtAsc(UUID conversationId);

}
