package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    public final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
