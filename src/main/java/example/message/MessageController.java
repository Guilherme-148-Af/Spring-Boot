
package example.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/messages")
class MessageController {

    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    record CreateMessageRequest(String name, String mensagem) {
    }

    @PostMapping
    ResponseEntity<Message> createMessage(
            @RequestBody CreateMessageRequest request,
            UriComponentsBuilder ucb
    ) {
        Message savedMessage = messageService.createMessage(request.name(), request.mensagem());
        URI locationOfNewMessage = ucb
                .path("messages/{id}")
                .buildAndExpand(savedMessage.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewMessage).body(savedMessage);
    }

    @GetMapping("/{id}")
    ResponseEntity<Message> findById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}