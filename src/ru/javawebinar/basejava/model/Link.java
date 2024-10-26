package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Link {
    private final String name;
    private final String url;
    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + " (" + url + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return name.equals(link.name) && Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
