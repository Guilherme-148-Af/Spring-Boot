package example.message;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
class MessageService {

    private final MessageRepository messageRepository;

    MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(String name, String mensagem) {
        Message newMessage = new Message(name, mensagem);
        return messageRepository.save(newMessage);
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }
}
