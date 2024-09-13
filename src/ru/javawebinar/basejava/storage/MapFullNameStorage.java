package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage<String> {
    protected Map<String, Resume> storage = new LinkedHashMap<>();

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
    protected List<Resume> getAllResumes() {
        List<Resume> storageList = new ArrayList<>(storage.values());
        storageList.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return storageList;
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
        return (new Resume(uuid)).getFullName();
    }
}
