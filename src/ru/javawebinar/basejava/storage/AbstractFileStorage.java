package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void clearResumes() {
        File[] files = getFilesList();
        for (File file : files) {
            file.delete();
        }
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            writeResume(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error while updating resume in file ", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume resume) {
        try {
            file.createNewFile();
            writeResume(file, resume);
        } catch (IOException e) {
            throw new StorageException("IO error while saving resume in file ", file.getName(), e);
        }
    }

    protected abstract void writeResume(File file, Resume resume) throws IOException;

    @Override
    protected Resume getResume(File file) {
        try {
            return readResume(file);
        } catch (IOException e) {
            throw new StorageException("IO error while reading resume from file ", file.getName(), e);
        }
    }

    protected abstract Resume readResume(File file) throws IOException;

    @Override
    protected void deleteResume(File file) {
        file.delete();
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
            throw new StorageException("Null directory ", directory.getName());
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
