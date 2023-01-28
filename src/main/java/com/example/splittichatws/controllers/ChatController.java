package nehe.chatappapi.controllers;


import nehe.chatappapi.Modals.Message;
import nehe.chatappapi.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class ChatController {

    private ChatService chatService;

    @Autowired
    public  ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){

        return  ResponseEntity.ok(chatService.getAllUsers());
    }

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        return  ResponseEntity.status(HttpStatus.CREATED).body(chatService.sendMessage(message));
    }

    @PutMapping("/message/messageId/{messageId}")
    public ResponseEntity<?>  updateMessageToRead(@PathVariable String messageId){
        return  ResponseEntity.ok(chatService.updateMessageToRead(messageId));
    }

    @GetMapping("/messages/receiverId/{receiverId}")
    public ResponseEntity<?> getMessagesByReceiver(@PathVariable String receiverId){
        return ResponseEntity.ok( chatService.getMessagesByReceiver( receiverId ));
    }

    @GetMapping("/messages/receiverId/{receiverId}/senderId/{senderId}")
    public ResponseEntity<?> getMessagesByReceiverAndSender(@PathVariable String receiverId, @PathVariable String senderId){
        return ResponseEntity.ok( chatService.getMessagesByReceiverAndSender( receiverId ,senderId));
    }

    @DeleteMapping("/message/messageId/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable String messageId){
        chatService.deleteMessage(messageId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @PutMapping("/message/id/{messageId}")
    public ResponseEntity<?> updateMessageText(@PathVariable String messageId, @RequestParam String text){
        return  ResponseEntity.ok(chatService.updateMessageText( messageId , text));
    }
}
