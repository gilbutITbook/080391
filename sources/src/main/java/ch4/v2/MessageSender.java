package ch4.v2;

import java.util.List;

public class MessageSender {
    private Bot bot;
    private UserDirectory userDirectory;
    private MessageRepository repository;
    private EmailSender emailSender;         // 1
    private UserPreferences userPrefs;       // 1
    public MessageSender(Bot bot,
                         UserDirectory userDirectory,
                         MessageRepository repository,
                         EmailSender emailSender,
                         UserPreferences userPrefs) {
        this.bot = bot;
        this.userDirectory = userDirectory;
        this.repository = repository;
        this.emailSender = emailSender;
        this.userPrefs = userPrefs;
    }
    public void sendMessages() {
        List<Message> messagesToBeSent = repository.getMessagesToBeSent();
        for(Message messageToBeSent : messagesToBeSent) {
            String userId = userDirectory.getAccount(messageToBeSent.getEmail());
            bot.sendPrivateMessage(userId, messageToBeSent.getBodyInMarkdown());
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
interface Bot {
    void sendPrivateMessage(String userId, String messageToBeSent);
}
interface UserDirectory {
    String getAccount(String email);
}
interface MessageRepository {
    List<Message> getMessagesToBeSent();
}

class Message {
    private String email;
    private String body;
    private boolean sent;
    public Message(String email, String body) {
        this.email = email;
        this.body = body;
        this.sent = false;
    }
    public String getEmail() {
        return email;
    }
    public String getBodyInMarkdown() {
        // body를 마크다운 형식으로 변환
        return body;
    }
    public void markAsSent() {
        this.sent = true;
    }
}

