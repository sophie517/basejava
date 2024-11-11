package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.ObjectStreamSerialization;

class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STRING_STORAGE_DIR, new ObjectStreamSerialization()));
    }
}