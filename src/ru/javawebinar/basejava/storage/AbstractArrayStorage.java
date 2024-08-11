package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
            System.out.println("Резюме обновлено");
        } else {
            System.out.println("Ошибка: такого резюме нет");
        }
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: storage переполнено");
        } else if (isExist(index)) {
            System.out.println("Ошибка: такое резюме уже есть");
        } else {
            storage[getInsertionPoint(index)] = resume;
            size++;
        }
    }

    protected abstract int getInsertionPoint(int index);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Резюме " + uuid + " не существует");
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            System.out.println("Ошибка: такого резюме нет");
        } else {
            remove(index);
            size--;
        }
    }

    protected abstract void remove(int index);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected boolean isExist(int index) {
        return index > -1;
    }
}