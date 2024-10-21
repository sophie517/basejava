package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;
    private final List<Contact> contacts = new ArrayList<>();
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
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
        System.out.println();
        Iterator<Map.Entry<SectionType, Section>> iterator = sections.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<SectionType, Section> entry = iterator.next();
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

    public void setContact(ContactType type, String content) {
        contacts.add(new Contact(type, content));
    }

    public void setSection(SectionType type, Section section) {
        sections.put(type, section);
    }
}
