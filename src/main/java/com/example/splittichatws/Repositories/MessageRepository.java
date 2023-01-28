package nehe.chatappapi.Repositories;

import nehe.chatappapi.Modals.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository  extends MongoRepository<Message, String> {
}
