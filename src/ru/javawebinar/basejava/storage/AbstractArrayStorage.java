package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void clearResumes() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    protected void saveResume(int index, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Ошибка: storage переполнено", resume.getUuid());
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    protected abstract void insertResume(int index, Resume resume);

    protected Resume getResume(int index) {
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected void deleteResume(int index) {
        removeResume(index);
        size--;
    }

    protected abstract void removeResume(int index);

    protected Resume[] getAllResumes() {
        return Arrays.copyOf(storage, size);
    }

    protected int getSize() {
        return size;
    }

    protected boolean isExist(int index) {
        return index > -1;
    }
}