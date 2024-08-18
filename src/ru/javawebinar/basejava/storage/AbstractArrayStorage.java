package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
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
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Ошибка: storage переполнено", resume.getUuid());
        } else if (isExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    protected abstract void insertResume(int index, Resume resume);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            removeResume(index);
            size--;
        }
    }

    protected abstract void removeResume(int index);

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