package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.ThrowingExceptionConsumer;
import ru.javawebinar.basejava.util.ThrowingExceptionSupplier;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void writeResume(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = SectionType.valueOf(entry.getKey().name());
                dos.writeUTF(sectionType.toString());
                dos.writeUTF(sectionType.toString());
                Section section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(section.toString());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> elements = ((ListSection) section).getElements();
                        writeWithException(elements, dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Company> companies = ((CompanySection) section).getCompanies();
                        writeWithException(companies, dos, company -> {
                            dos.writeUTF(company.getName());
                            dos.writeUTF(String.valueOf(company.getLink()));

                            List<Period> periods = company.getPeriods();
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

            Map<ContactType, String> contacts = readWithException(dis,
                    () -> ContactType.valueOf(dis.readUTF()), dis::readUTF);
            contacts.forEach(resume::setContact);

            Map<SectionType, Section> sections = readWithException(dis,
                    () -> SectionType.valueOf(dis.readUTF()),
                    () -> {
                        SectionType type = SectionType.valueOf(dis.readUTF());

                        return switch (type) {
                            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());

                            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readWithException(dis, dis::readUTF));

                            case EXPERIENCE, EDUCATION -> new CompanySection(readWithException(dis, () -> {
                                String name = dis.readUTF();
                                String url = dis.readUTF();

                                List<Period> periods = readWithException(dis, () -> {
                                    LocalDate startDate = LocalDate.parse(dis.readUTF());
                                    LocalDate endDate = LocalDate.parse(dis.readUTF());
                                    String position = dis.readUTF();
                                    String description = dis.readUTF();
                                    return new Period(startDate, endDate, position, description);
                                });
                                return new Company(name, url, periods);
                            }));
                        };
                    });
            sections.forEach(resume::setSection);
            return resume;
        }
    }

    public static <T> void writeWithException(Collection<T> collection, DataOutputStream dos,
                                              ThrowingExceptionConsumer<T> consumer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            consumer.accept(element);
        }
    }

    public static <T> List<T> readWithException(DataInputStream dis,
                                              ThrowingExceptionSupplier<T> supplier) throws IOException {
        int size = dis.readInt();
        List<T> collection = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            collection.add(supplier.get());
        }
        return collection;
    }

    public static <K, V> Map<K, V> readWithException(DataInputStream dis,
                                                     ThrowingExceptionSupplier<K> keySupplier,
                                                     ThrowingExceptionSupplier<V> valueSupplier) throws IOException {
        int size = dis.readInt();
        Map<K, V> collection = new LinkedHashMap<>(size);
        for (int i = 0; i < size; i++) {
            K key = keySupplier.get();
            V value = valueSupplier.get();
            collection.put(key, value);
        }
        return collection;
    }
}

