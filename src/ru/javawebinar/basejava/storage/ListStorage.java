package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {
    protected  List<Resume> storage = new ArrayList<>();

    @Override
    protected void clearResumes() {
        storage.clear();
    }

    @Override
    protected void updateResume(Integer index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void saveResume(Integer index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    protected void deleteResume(Integer index) {
        storage.remove(storage.get(index));
    }

    @Override
    protected List<Resume> getAllResumes() {
        storage.sort(Comparator.comparing(Resume::getUuid));
        return storage;
    }

    @Override
    protected int getSize() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != -1;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}