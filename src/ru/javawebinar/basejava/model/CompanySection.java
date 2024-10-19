package ru.javawebinar.basejava.model;

import java.util.List;

public class CompanySection extends Section {
    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public String toString() {
        for (Company company : companies) {
            System.out.println(company);
        }
        return "";
    }

    public int hashCode() {
        return companies.hashCode();
    }
}
