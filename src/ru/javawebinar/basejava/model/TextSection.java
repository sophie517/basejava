package ru.javawebinar.basejava.model;

public class TextSection extends Section {
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection info = (TextSection) o;

        return content.equals(info.content);
    }

    public int hashCode() {
        return content.hashCode();
    }

    public String toString() {
        return content + "\n";
    }
}
