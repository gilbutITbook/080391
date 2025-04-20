package ch5.v5;

public class MessageBot {
    private Bot bot;
    private UserDirectory userDirectory;
    public MessageBot(Bot bot,
                      UserDirectory userDirectory) {          // 1
        this.bot = bot;
        this.userDirectory = userDirectory;
    }
    public void send(Message msg) {          // 2
        String userId = userDirectory.getAccount(msg.getEmail());
        bot.sendPrivateMessage(userId, msg.getBodyInMarkdown());
    }
}