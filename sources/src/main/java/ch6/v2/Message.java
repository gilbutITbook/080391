package ch6.v2;

public class Message {
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
