package demo.demo.controller;

import demo.demo.constants.Constants;
import demo.demo.dto.ResponseGeneral;
import demo.demo.dto.conversationDTO.ConversationRequest;
import demo.demo.entities.Conversation;
import demo.demo.services.ConversationServices;
import demo.demo.utilis.JwtUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private ConversationServices conversationServices;

    @Autowired
    private JwtUtilis jwtUtilis;

    @PostMapping("/create")
    public ResponseEntity<ResponseGeneral<Conversation>> createConvo(@RequestBody ConversationRequest payload){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            return ResponseEntity.ok(ResponseGeneral.success(conversationServices.createConversation(UUID.fromString(authentication.getName()), jwtUtilis.extractIsSeller(token),payload), "Conversation Created Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseGeneral<List<Conversation>> >getConversations() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            List<Conversation> conversations = conversationServices.getConversations(authentication.getName(), jwtUtilis.extractIsSeller(token));
            return ResponseEntity.ok(ResponseGeneral.success(conversations, "Conversation fetch Successfully"));
        }
        catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneral<Conversation>> updateConversation() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            Conversation updatedConvo = conversationServices.updateConversation(UUID.fromString(authentication.getName()), jwtUtilis.extractIsSeller(token));
            if (updatedConvo != null) {
                return ResponseEntity.ok(ResponseGeneral.success(updatedConvo, "Conversation fetch Successfully"));
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeneral<Conversation>> getSingleConversation(@PathVariable UUID id) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Conversation conversation = conversationServices.getSingleConversation(id);
            return ResponseEntity.ok(ResponseGeneral.success(conversation, "Conversation fetch Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(Constants.internlServerError).body(ResponseGeneral.error(Constants.internlServerError, e.getMessage()));
        }
    }
}
