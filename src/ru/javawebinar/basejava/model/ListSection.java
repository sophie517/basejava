package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.List;

public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> elements;

    public ListSection() {
    }

    public ListSection(List<String> elements) {
        this.elements = elements;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection list = (ListSection) o;

        return elements.equals(list.elements);
    }

    public int hashCode() {
        return elements.hashCode();
    }

    public String toString() {
        for (String element : elements) {
            System.out.println("- " + element);
        }
        return "";
    }

    public List<String> getElements() {
        return elements;
    }
}
