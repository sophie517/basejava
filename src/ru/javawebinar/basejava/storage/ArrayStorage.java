package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected void insertResume(Integer index, Resume resume) {
        storage[size] = resume;
    }

    protected void removeResume(Integer index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}