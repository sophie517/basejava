package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
            System.out.println("Резюме обновлено");
        } else {
            System.out.println("Ошибка: такого резюме нет");
        }
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: storage переполнено");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("Ошибка: такое резюме уже есть");
        } else {
            storage[size] = resume;
            size++;
        }
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

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
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
