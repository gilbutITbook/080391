package ch7.v1;

public class BotMessage {
    private String id;
    private String message;

    public BotMessage(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
}
