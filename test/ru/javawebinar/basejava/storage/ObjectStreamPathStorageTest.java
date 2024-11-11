package ru.javawebinar.basejava.storage;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STRING_STORAGE_DIR));
    }
}