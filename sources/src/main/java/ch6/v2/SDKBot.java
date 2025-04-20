package ch6.v2;

public class SDKBot implements Bot {
    public void sendPrivateMessage(String userId, String msg) {
        var chatBot = new ChatBotV1();                // 1
        var message = new BotMessage(userId, msg);    // 2
        chatBot.writeMessage(message);                // 3
    }
}