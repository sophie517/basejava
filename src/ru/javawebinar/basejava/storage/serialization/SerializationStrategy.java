package ru.javawebinar.basejava.storage.serialization;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void writeResume(OutputStream os, Resume resume) throws IOException;

    Resume readResume(InputStream is) throws IOException;
}
