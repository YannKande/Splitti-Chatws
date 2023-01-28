package nehe.chatappapi.Services;

import com.mongodb.client.result.UpdateResult;
import nehe.chatappapi.Modals.Message;
import nehe.chatappapi.Modals.User;
import nehe.chatappapi.Modals.UserRes;
import nehe.chatappapi.Repositories.MessageRepository;
import nehe.chatappapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ChatService {

    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private  MongoOperations mongoOperations;

    @Autowired
    public ChatService(UserRepository userRepository, MessageRepository messageRepository,MongoOperations mongoOperations){
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.mongoOperations = mongoOperations;
    }

    public List<UserRes> getAllUsers(){

        List<User> users = userRepository.findAll();

        var userRes = new ArrayList<UserRes>();

        users.forEach( user -> {

            var modifiedUser = new UserRes( user.getId(), user.getFirstName() ,
                    user.getLastName(), user.getEmail(), user.getRole());


            userRes.add( modifiedUser);
        });

        return  userRes;

    }

    public Message sendMessage(Message message){
       return messageRepository.save(message);
    }

    public Message updateMessageToRead(String messageId){

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(messageId));

        Update update = new Update();
        update.set("isRead", true);

         mongoOperations.updateFirst( query, update , Message.class);

        return mongoOperations.findOne(query, Message.class);
    }

    public List<Message> getMessagesByReceiver(String receiverId){

        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(receiverId));

        return mongoOperations.find(query, Message.class);
    }

    public List<Message> getMessagesByReceiverAndSender(String receiverId, String senderId){

        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(receiverId).and("senderId").is(senderId));

        return mongoOperations.find(query, Message.class);
    }

    public void deleteMessage(String messageId){

        var query = new Query();
        query.addCriteria( Criteria.where("_id").is(messageId));
        mongoOperations.remove(query, Message.class);
    }

    public Message updateMessageText(String messageId , String text){

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(messageId));

        Update update = new Update();
        update.set("text", text);

        mongoOperations.updateFirst( query, update , Message.class);

        return mongoOperations.findOne(query, Message.class);

    }
}
