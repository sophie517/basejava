package ru.javawebinar.basejava.model;

import java.io.Serial;

public class TextSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

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
