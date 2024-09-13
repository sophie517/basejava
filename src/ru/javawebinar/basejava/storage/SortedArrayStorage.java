package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected void insertResume(Integer index, Resume resume) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    protected void removeResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
    }

    protected Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "name"), Comparator.comparing(Resume::getUuid));
    }
}