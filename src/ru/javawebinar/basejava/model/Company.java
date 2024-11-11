package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final List<Period> periods;
    private final Link link;

    public Company(String name, String url, List<Period> periods) {
        this.name = name;
        this.periods = periods;
        this.link = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return name.equals(company.name) && periods.equals(company.periods);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        System.out.println(link);
        for (Period period : periods) {
            System.out.println(period);
        }
        return "";
    }
}
