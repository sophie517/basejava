package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void clearResumes() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void updateResume(Integer index, Resume resume) {
        storage[index] = resume;
    }

    protected void saveResume(Integer index, Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Ошибка: storage переполнено", resume.getUuid());
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    protected abstract void insertResume(Integer index, Resume resume);

    protected Resume getResume(Integer index) {
        return storage[index];
    }

    protected void deleteResume(Integer index) {
        removeResume(index);
        size--;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected abstract void removeResume(Integer index);

    protected int getSize() {
        return size;
    }

    protected boolean isExist(Integer index) {
        return index > -1;
    }

    protected abstract Integer getSearchKey(String uuid);
}