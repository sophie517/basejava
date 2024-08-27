package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    public final void clear() {
        clearResumes();
    }

    protected abstract void clearResumes();

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            updateResume(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void updateResume(int index, Resume resume);

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(index, resume);
        }
    }

    protected abstract void saveResume(int index, Resume resume);

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    protected abstract Resume getResume(int index);

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    protected abstract void deleteResume(int index);

    public final Resume[] getAll() {
        return getAllResumes();
    }

    protected abstract Resume[] getAllResumes();

    public final int size() {
        return getSize();
    }

    protected abstract int getSize();

    protected abstract boolean isExist(int index);

    protected abstract int getIndex(String uuid);
}
