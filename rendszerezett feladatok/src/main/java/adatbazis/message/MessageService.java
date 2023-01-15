package adatbazis.message;

import java.util.List;
import java.util.Optional;

public class MessageService {

private UserRepository userRepository;
private MessageRepository messageRepository;

    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void registerUser(String username){
        if(!isUserNameTaken(username)){
            throw new IllegalArgumentException("Username is already taken: " + username);
        }
        userRepository.insertUser(username);
    }

    private boolean isUserNameTaken(String username) {
        return userRepository.findUserByName(username).stream().filter(u -> u.getUsername().equals(username)).toList().isEmpty();
    }

    public void sendMessage(User sender, User receiver, String message){
        Optional<User> actualSender = userRepository.findUserByName(sender.getUsername());
        Optional<User> actualReceiver = userRepository.findUserByName(receiver.getUsername());

        if (actualReceiver.isPresent() && actualSender.isPresent()) {
            messageRepository.insertMessage(actualSender.get().getId(), actualReceiver.get().getId(), message);
        } else {
            throw new IllegalArgumentException("Sender or receiver not found!");
        }
    }


    public List<String> findMessagesBySenderId(long sender_id){
        return messageRepository.findMessagesBySenderId(sender_id);
    }
}
