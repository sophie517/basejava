package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.ObjectStreamSerialization;

class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerialization()));
    }
}