package ru.gb.mall.inventory.mail.message;

import java.util.Objects;

public final class AttachmentMailMessage {
    private final String from;
    private final String to;
    private final String subject;
    private final String text;
    private final EmailAttachment attachment;

    AttachmentMailMessage(
            String from,
            String to,
            String subject,
            String text,
            EmailAttachment attachment
    ) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
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

    public EmailAttachment attachment() {
        return attachment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AttachmentMailMessage) obj;
        return Objects.equals(this.from, that.from) &&
                Objects.equals(this.to, that.to) &&
                Objects.equals(this.subject, that.subject) &&
                Objects.equals(this.text, that.text) &&
                Objects.equals(this.attachment, that.attachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, text, attachment);
    }

    @Override
    public String toString() {
        return "AttachmentMailMessage[" +
                "from=" + from + ", " +
                "to=" + to + ", " +
                "subject=" + subject + ", " +
                "text=" + text + ", " +
                "attachment=" + attachment + ']';
    }

}
