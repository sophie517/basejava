package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.SerializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final SerializationStrategy strategy;

    protected FileStorage(File directory, SerializationStrategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategy = strategy;
    }

    @Override
    protected void clearResumes() {
        File[] files = getFilesList();
        for (File file : files) {
            deleteResume(file);
        }
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            strategy.writeResume(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException("IO error while updating resume in file ", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error while saving resume in file ", file.getName(), e);
        }
        updateResume(file, resume);
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return strategy.readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error while reading resume from file ", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Failed to delete file ", file.getName());
        }
    }

    @Override
    protected List<Resume> getAllResumes() {
        File[] files = getFilesList();
        List<Resume> resumes = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                resumes.add(getResume(file));
            }
        }
        return resumes;
    }

    @Override
    protected int getSize() {
        File[] files = getFilesList();
        return files.length;
    }

    private File[] getFilesList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("IO error while getting files from directory ", directory.getName());
        }
        return files;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }
}
