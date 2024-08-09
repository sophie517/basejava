package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: storage переполнено");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("Ошибка: такое резюме уже есть");
        } else {
            int index = -(Arrays.binarySearch(storage, 0, size, resume) + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (!isExist(index)) {
            System.out.println("Ошибка: такого резюме нет");
        } else {
            System.arraycopy(storage, index + 1, storage, index, (size - index - 1));
            size--;
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
