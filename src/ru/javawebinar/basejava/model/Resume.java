package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * ru.javawebinar.basejava.model.Resume class
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Resume implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new LinkedHashMap<>();
    private final Map<SectionType, Section> sections = new LinkedHashMap<>();

    public Resume() {
    }

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
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "Resume {" +
                "uuid = '" + uuid + '\'' +
                ", fullName = '" + fullName + '\'' +
                ", contacts = " + contacts +
                ", sections = " + sections +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public void setSection(SectionType type, Section section) {
        sections.put(type, section);
    }
}
