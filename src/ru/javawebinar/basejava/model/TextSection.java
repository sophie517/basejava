package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

    private String content;

    public TextSection() {
    }

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
        return content;
    }
}
