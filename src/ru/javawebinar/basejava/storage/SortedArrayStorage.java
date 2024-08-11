package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected int getInsertionPoint(int index) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        return index;
    }

    protected void remove(int index) {
        System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
