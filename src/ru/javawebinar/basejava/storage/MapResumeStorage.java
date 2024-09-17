package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void clearResumes() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void deleteResume(Resume searchKey) {
        storage.remove(searchKey.getUuid());
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
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
