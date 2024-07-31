import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int numOfResumes = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    void save(Resume resume) {
        storage[numOfResumes++] = resume;
    }

    Resume get(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[numOfResumes - 1];
                storage[numOfResumes - 1] = null;
                numOfResumes--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numOfResumes);
    }

    int size() {
        return numOfResumes;
    }
}
