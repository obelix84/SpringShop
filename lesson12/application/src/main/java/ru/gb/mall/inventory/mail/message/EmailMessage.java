package ru.gb.mall.inventory.mail.message;

import java.util.Objects;

public final class EmailMessage {
    private final String from;
    private final String to;
    private final String subject;
    private final String text;

    public EmailMessage(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public String text() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (EmailMessage) obj;
        return Objects.equals(this.from, that.from) &&
                Objects.equals(this.to, that.to) &&
                Objects.equals(this.subject, that.subject) &&
                Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, text);
    }

    @Override
    public String toString() {
        return "EmailMessage[" +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "subject=" + subject + ", " +
                "text=" + text + ']';
    }

}
