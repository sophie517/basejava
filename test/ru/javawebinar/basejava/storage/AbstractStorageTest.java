package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    private static final String UUID_0 = UUID.randomUUID().toString();
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();

    private static final Resume RESUME_0 = ResumeTestData.createResume(UUID_0, "name0");
    public static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, "name1");
    private static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, "name2");


    private static final String UUID_NOT_EXIST = "dummy";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_0);
        storage.save(RESUME_1);
        storage.save(RESUME_2);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertNotExist(UUID_0);
        assertNotExist(UUID_1);
        assertNotExist(UUID_2);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "name2");
        storage.update(newResume);
        Assertions.assertEquals(storage.get(UUID_1), newResume);
    }

    @Test
    public void updateNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        assertGet(RESUME_0);
        assertGet(RESUME_1);
        assertGet(RESUME_2);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void get() {
        assertGet(RESUME_0);
        assertGet(RESUME_1);
        assertGet(RESUME_2);
    }

    @Test
    public void getNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertNotExist(UUID_1);
    }

    @Test
    public void deleteNotExist() {
        assertNotExist(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        assertSize(3);
        List<Resume> resumes = List.of(new Resume[]{RESUME_0, RESUME_1, RESUME_2});
        List<Resume> newResumes = storage.getAllSorted();
        Assertions.assertEquals(resumes, newResumes);
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