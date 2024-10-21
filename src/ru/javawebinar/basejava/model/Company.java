package ru.javawebinar.basejava.model;

import java.util.*;

public class Company {
    private final String name;
    private final List<Period> periods;

    public Company(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
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
        System.out.println(name);
        for (Period period : periods) {
            System.out.println(period);
        }
        return "";
    }
}
