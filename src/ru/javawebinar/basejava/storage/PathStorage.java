package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.serialization.SerializationStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy strategy;

    protected PathStorage(String dir, SerializationStrategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.strategy = strategy;
    }

    @Override
    protected void clearResumes() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        try (OutputStream os = Files.newOutputStream(path)) {
            strategy.writeResume(os, resume);
        } catch (IOException e) {
            throw new StorageException("IO error while updating resume in file ", path.toString(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume resume) {
        try {
            Files.createFile(path);
            try (OutputStream os = Files.newOutputStream(path)) {
                strategy.writeResume(os, resume);
            }
        } catch (IOException e) {
            throw new StorageException("IO error while saving resume in file ", path.toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            return strategy.readResume(is);
        } catch (IOException e) {
            throw new StorageException("IO error while reading resume from file ", path.toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file ", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> getAllResumes() {
        try {
            return Files.list(directory)
                    .filter(Files::isRegularFile)
                    .map(this::getResume)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO error while getting files from directory ", directory.toString(), e);
        }
    }

    @Override
    protected int getSize() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("IO error while getting directory size ", directory.toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }
}
