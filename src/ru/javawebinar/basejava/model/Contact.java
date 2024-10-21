package ru.javawebinar.basejava.model;

public class Contact {
    private final ContactType type;
    private final String content;

    public Contact(ContactType type, String content) {
        this.type = type;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return type == contact.type && content.equals(contact.content);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return type.getTitle() + ": " + content;
    }
}
