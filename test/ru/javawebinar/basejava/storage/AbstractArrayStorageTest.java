package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    protected void overflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("name"));
            }
        } catch (StorageException exception) {
            Assertions.fail();
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume("name")));
    }
}