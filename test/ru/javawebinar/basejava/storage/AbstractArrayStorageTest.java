package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private static Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        AbstractArrayStorageTest.storage = storage;
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
        checkExistence(UUID_1);
        checkExistence(UUID_2);
        checkExistence(UUID_3);
    }

    @Test
    public void update() {
        storage.update(resume2);
        Assertions.assertSame(storage.get(UUID_2), resume2);
    }

    @Test
    public void updateNotExist() {
        checkExistence("dummy");
    }

    @Test
    public void save() {
        Assertions.assertSame(storage.get(UUID_1), resume1);
        Assertions.assertSame(storage.get(UUID_2), resume2);
        Assertions.assertSame(storage.get(UUID_3), resume3);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume2));
    }

    @Test
    public void overflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException exception) {
            Assertions.fail();
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    public void get() {
        Assertions.assertSame(storage.get(UUID_1), resume1);
        Assertions.assertSame(storage.get(UUID_2), resume2);
        Assertions.assertSame(storage.get(UUID_3), resume3);
    }

    @Test
    public void getNotExist() {
        checkExistence("dummy");
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assertions.assertEquals(2, storage.size());
        checkExistence(UUID_2);
    }

    @Test
    public void deleteNotExist() {
        checkExistence("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assertions.assertEquals(3, resumes.length);
        Assertions.assertSame(resumes[0], resume1);
        Assertions.assertSame(resumes[1], resume2);
        Assertions.assertSame(resumes[2], resume3);
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }

    private void checkExistence(String uuid) {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(uuid));
    }

}