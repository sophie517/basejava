package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {
    private final List<String> elements;

    public ListSection(List<String> elements) {
        this.elements = elements;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection list = (ListSection) o;

        return elements.equals(list.elements);
    }

    public String toString() {
        for (String element : elements) {
            System.out.println("- " + element);
        }
        return "";
    }

    public int hashCode() {
        return elements.hashCode();
    }
}
