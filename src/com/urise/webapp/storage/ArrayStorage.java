package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: storage переполнено");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("Ошибка: такое резюме уже есть");
        } else {
            storage[size++] = resume;
        }
    }

    public void update(Resume resume, String newUuid) {
        if (isExist(getIndex(resume.getUuid()))) {
            resume.setUuid(newUuid);
            System.out.println("Резюме обновлено");
        } else {
            System.out.println("Ошибка: такого резюме нет");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        System.out.println("Ошибка: такого резюме нет");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка: такого резюме нет");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(int index) {
        return index != -1;
    }
}
