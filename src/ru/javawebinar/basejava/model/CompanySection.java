package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.List;

public class CompanySection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return companies.equals(that.companies);
    }

    public int hashCode() {
        return companies.hashCode();
    }

    public String toString() {
        for (Company company : companies) {
            System.out.println(company);
        }
        return "";
    }
}
