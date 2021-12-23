package ru.gb.mall.inventory.mail.message;

import java.io.InputStream;
import java.util.Objects;

public final class EmailAttachment {
    private final String name;
    private final InputStream resource;

    EmailAttachment(String name, InputStream resource) {
        this.name = name;
        this.resource = resource;
    }

    public String name() {
        return name;
    }

    public InputStream resource() {
        return resource;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (EmailAttachment) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resource);
    }

    @Override
    public String toString() {
        return "EmailAttachment[" +
                "name=" + name + ", " +
                "resource=" + resource + ']';
    }

}
