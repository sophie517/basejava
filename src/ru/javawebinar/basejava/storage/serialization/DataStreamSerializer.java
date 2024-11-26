package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.ThrowingExceptionConsumer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void writeResume(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = SectionType.valueOf(entry.getKey().name());
                dos.writeUTF(sectionType.toString());
                Section section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(section.toString());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> elements = ((ListSection) section).getElements();
                        dos.writeInt(elements.size());
                        for (String element : elements) {
                            dos.writeUTF(element);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Company> companies = ((CompanySection) section).getCompanies();
                        dos.writeInt(companies.size());
                        writeWithException(companies, dos, company -> {
                            dos.writeUTF(company.getName());
                            dos.writeUTF(String.valueOf(company.getLink()));

                            List<Period> periods = company.getPeriods();
                            dos.writeInt(periods.size());
                            writeWithException(periods, dos, period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDescription());
                            });
                        });
                    }
                    default ->
                            throw new IOException("Unknown section type to write: " + sectionType);
                }
            });
        }
    }

    @Override
    public Resume readResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());

                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        String content = dis.readUTF();
                        resume.setSection(type, new TextSection(content));
                    }

                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int listSize = dis.readInt();
                        List<String> items = new ArrayList<>();
                        for (int j = 0; j < listSize; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.setSection(type, new ListSection(items));
                    }

                    case EXPERIENCE, EDUCATION -> {
                        int companiesSize = dis.readInt();
                        List<Company> companies = new ArrayList<>();
                        for (int j = 0; j < companiesSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            List<Period> periods = new ArrayList<>();

                            int periodsSize = dis.readInt();
                            for (int k = 0; k < periodsSize; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String position = dis.readUTF();
                                String description = dis.readUTF();
                                periods.add(new Period(startDate, endDate, position, description));
                            }
                            companies.add(new Company(name, url, periods));
                        }
                        resume.setSection(type, new CompanySection(companies));
                    }

                    default ->
                        throw new IOException("Unknown section type to read: " + type);

                }
            }
            return resume;
        }
    }

    public static <T> void writeWithException(Collection<T> collection, DataOutputStream dos,
                                              ThrowingExceptionConsumer<T> consumer) throws IOException {
        for (T element : collection) {
            consumer.accept(element);
        }
    }
}

