package ch5.v3;

import java.util.List;

public class MessageSender {
    private MessageBot messageBot;
    private MessageRepository repository;
    private EmailSender emailSender;         // 1
    private UserPreferences userPrefs;       // 1
    public MessageSender(MessageBot bot,
                         MessageRepository repository,
                         EmailSender emailSender,
                         UserPreferences userPrefs) {
        this.messageBot = bot;
        this.repository = repository;
        this.emailSender = emailSender;
        this.userPrefs = userPrefs;
    }
    public void sendMessages() {
        List<Message> messagesToBeSent = repository.getMessagesToBeSent();
        for(Message messageToBeSent : messagesToBeSent) {
            messageBot.send(messageToBeSent);
            if(userPrefs.sendViaEmail(messageToBeSent.getEmail())) {   // 2
                emailSender.sendMessage(messageToBeSent);
            }
            // 메시지를 보낸 것으로 표시한다
            messageToBeSent.markAsSent();
        }
    }
}
interface EmailSender {
    void sendMessage(Message message);
}
interface UserPreferences {
    boolean sendViaEmail(String email);
}

interface MessageRepository {
    List<Message> getMessagesToBeSent();
}

