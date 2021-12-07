package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private final int id;
    private User from;
    private List<User> to;
    private String message;
    private final LocalDateTime date;
    private final Message reply;

    public Message(int id, User from, List<User> to, String message, Message reply) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = LocalDateTime.now();
        this.reply = reply;
    }

    public int getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public List<User> getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Message getReply() {
        return reply;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(List<User> to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  "|from: " + this.from + "\n" +
                "|to: " + this.to + "\n" +
                "| -> " + this.message + "\n" +
                "|____________________________\n"+
                "| at: " + this.date + "\n" +
                "|____________________________";
    }
}
