package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private int indexOfResume;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Ошибка: storage переполнено");
        } else if (isResumePresent(resume.getUuid())) {
            System.out.println("Ошибка: такое резюме уже есть");
        } else {
            storage[size++] = resume;
        }
    }

    public void update(Resume resume, String newUuid) {
        if (isResumePresent(resume.getUuid())) {
            resume.setUuid(newUuid);
            System.out.println("Резюме обновлено");
        } else {
            System.out.println("Ошибка: такого резюме нет");
        }
    }

    public Resume get(String uuid) {
        if (isResumePresent(uuid)) {
            return storage[indexOfResume];
        }
        System.out.println("Ошибка: такого резюме нет");
        return null;
    }

    public void delete(String uuid) {
        if (isResumePresent(uuid)) {
            storage[indexOfResume] = storage[size - 1];
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

    private boolean isResumePresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                indexOfResume = i;
                return true;
            }
        }
        return false;
    }
}
