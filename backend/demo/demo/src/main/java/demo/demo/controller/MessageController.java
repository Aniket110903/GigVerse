package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.ResponseGeneral;
import demo.demo.dto.messageDTO.MessageDto;
import demo.demo.entities.Message;
import demo.demo.services.MessageService;
import demo.demo.utilis.JwtUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private JwtUtilis jwtUtilis;

    @PostMapping
    public ResponseEntity<ResponseGeneral<Message>> createMessage(@RequestBody MessageDto request) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            Message message = messageService.createMessage(UUID.fromString(authentication.getName()), jwtUtilis.extractIsSeller(token), request.getConversationId(), request.getDescription());

            return ResponseEntity.ok(ResponseGeneral.success(message, "Order Created Successfully"));

        } catch (Exception e) {
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<ResponseGeneral<List<MessageDto>>> getMessages(@PathVariable UUID conversationId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<MessageDto> messages = messageService.getMessages(conversationId);
            return ResponseEntity.ok(ResponseGeneral.success(messages, "Order Created Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

}
