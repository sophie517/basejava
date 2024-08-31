package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapStorage extends AbstractStorage<String> {
    protected HashMap<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void clearResumes() {
        storage.clear();
    }

    @Override
    protected void updateResume(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void saveResume(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void deleteResume(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume[] getAllResumes() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected int getSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}