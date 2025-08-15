package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.util.JsonParser;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Failed to load PostgreSQL driver", e);
        }

        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
       sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume r SET full_name = ? WHERE r.uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }

            deleteContacts(r, conn);
            deleteSections(r, conn);
            insertContact(r, conn);
            insertSection(r, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContact(r, conn);
            insertSection(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        Resume r = sqlHelper.execute("SELECT * FROM resume WHERE uuid = ?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
        });

        sqlHelper.execute("SELECT * FROM contact WHERE resume_uuid = ?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContactFromResultSet(r, rs);
                }
            return null;
        });

        sqlHelper.execute("SELECT * FROM section WHERE resume_uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                addSectionFromResultSet(r, rs);
            }
            return null;
        });

        return r;
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumes = sqlHelper.execute("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
                ResultSet rs = ps.executeQuery();
                Map<String, Resume> result = new LinkedHashMap<>();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    result.put(uuid, new Resume(uuid, fullName));
                }

                return result;
        });

         sqlHelper.execute("SELECT * FROM contact", ps -> {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String resume_uuid = rs.getString("resume_uuid");
                    Resume r = resumes.get(resume_uuid);
                    addContactFromResultSet(r, rs);
                }
                return null;
        });

        return sqlHelper.execute("SELECT * FROM section", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String resume_uuid = rs.getString("resume_uuid");
                Resume r = resumes.get(resume_uuid);
                addSectionFromResultSet(r, rs);
            }

            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        });
    }

    private void deleteContacts(Resume r, Connection conn) throws SQLException {
        deleteAttributes(r, conn, "DELETE FROM contact WHERE resume_uuid = ?");

    }

    private void deleteSections(Resume r, Connection conn) throws SQLException {
        deleteAttributes(r, conn, "DELETE  FROM section WHERE resume_uuid = ?");
    }

    private void deleteAttributes(Resume r, Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }


    private void insertContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, info) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                Section section = e.getValue();
                ps.setString(3, JsonParser.write(section, Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContactFromResultSet(Resume r, ResultSet rs) throws SQLException {
        String info = rs.getString("info");
        String type = rs.getString("type");

        if (type != null && info != null) {
            r.setContact(ContactType.valueOf(rs.getString("type")), info);
        }
    }

    private void addSectionFromResultSet(Resume r, ResultSet rs) throws SQLException {
        String content = rs.getString("content");

        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            r.setSection(type, JsonParser.read(content, Section.class));
        }
    }
}