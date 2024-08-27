package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_NOT_EXIST = "dummy";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertNotExist(UUID_1);
        assertNotExist(UUID_2);
        assertNotExist(UUID_3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assertions.assertSame(storage.get(UUID_2), newResume);
    }

    @Test
    public void updateNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume2));
    }

    @Test
    protected abstract void overflow();

    @Test
    public void get() {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    public void getNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        assertNotExist(UUID_2);
    }

    @Test
    public void deleteNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        assertSize(3);
        Resume[] resumes = new Resume[]{resume1, resume2, resume3};
        Resume[] newResumes = storage.getAll();
        Assertions.assertArrayEquals(resumes, newResumes);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertNotExist(String uuid) {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(uuid));
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}