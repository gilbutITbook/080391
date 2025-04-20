package ch4.v1;

import java.util.List;

public class MessageSender {
    private Bot bot;
    private UserDirectory userDirectory;
    private MessageRepository repository;
    public MessageSender(Bot bot,
                         UserDirectory userDirectory,
                         MessageRepository repository) {
        this.bot = bot;
        this.userDirectory = userDirectory;
        this.repository = repository;
    }
    public void sendMessages() {
        List<Message> messagesToBeSent = repository.getMessagesToBeSent();
        for(Message messageToBeSent : messagesToBeSent) { // 1
            String userId = userDirectory.
                    getAccount(messageToBeSent.getEmail());      // 2
            bot.sendPrivateMessage(userId,
                    messageToBeSent.getBodyInMarkdown());       // 3
            messageToBeSent.markAsSent();               // 4
        }
    }
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