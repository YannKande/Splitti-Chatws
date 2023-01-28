package nehe.chatappapi.Repositories;

import nehe.chatappapi.Modals.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User,String> {
    User findByEmail(String email);
}
