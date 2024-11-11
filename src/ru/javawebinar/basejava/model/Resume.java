package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new LinkedHashMap<>();
    private final Map<SectionType, Section> sections = new LinkedHashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    public int hashCode() {
        int hashCode = uuid.hashCode();
        hashCode = 31 * hashCode + fullName.hashCode();
        hashCode = 31 * hashCode + contacts.hashCode();
        hashCode = 31 * hashCode + sections.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        System.out.println(fullName + "\n");
        Iterator<Map.Entry<ContactType, String>> contactIterator = contacts.entrySet().iterator();
        while (contactIterator.hasNext()) {
            Map.Entry<ContactType, String> entry = contactIterator.next();
            ContactType type = entry.getKey();
            String contact = entry.getValue();
            System.out.print(type.getTitle() + ": ");
            System.out.println(contact);
        }
        System.out.println();
        Iterator<Map.Entry<SectionType, Section>> sectionIterator = sections.entrySet().iterator();
        while (sectionIterator.hasNext()) {
            Map.Entry<SectionType, Section> entry = sectionIterator.next();
            SectionType type = entry.getKey();
            Section section = entry.getValue();
            System.out.println(type.getTitle());
            System.out.println(section.toString());
        }
        return "";
    }

    public String getFullName() {
        return fullName;
    }

    public void setContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public void setSection(SectionType type, Section section) {
        sections.put(type, section);
    }
}
