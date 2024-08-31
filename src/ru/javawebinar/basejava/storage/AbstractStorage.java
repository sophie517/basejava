package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<T> implements Storage {
    public final void clear() {
        clearResumes();
    }

    protected abstract void clearResumes();

    public final void update(Resume resume) {
        T searchKey = getExistingSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    protected abstract void updateResume(T searchKey, Resume resume);

    public final void save(Resume resume) {
        T searchKey = getNotExistingSearchKey(resume.getUuid());
        saveResume(searchKey, resume);
    }

    protected abstract void saveResume(T searchKey, Resume resume);

    public final Resume get(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey);
    }

    protected abstract Resume getResume(T searchKey);

    public final void delete(String uuid) {
        T searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    protected abstract void deleteResume(T searchKey);

    public final Resume[] getAll() {
        return getAllResumes();
    }

    protected abstract Resume[] getAllResumes();

    public final int size() {
        return getSize();
    }

    protected abstract int getSize();

    protected final T getExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected final T getNotExistingSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(T searchKey);

    protected abstract T getSearchKey(String uuid);
}
