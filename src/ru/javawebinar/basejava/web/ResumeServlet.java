package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        if (fullName == null || fullName.trim().isEmpty()) {
            response.sendRedirect("resume?error=empty_name");
            return;
        }

        Resume r;
        boolean isNew = "new".equals(uuid);

        if (isNew) {
            r = new Resume(fullName.trim());
        } else {
            try {
                r = storage.get(uuid);
                r.setFullName(fullName.trim());
            } catch (NotExistStorageException e) {
                throw new IllegalArgumentException("Резюме с UUID " + uuid + " не существует", e);
            }
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                r.setContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : new SectionType[]{SectionType.PERSONAL, SectionType.OBJECTIVE}) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                r.setSection(type, new TextSection(value.trim()));
            } else {
                r.getSections().remove(type);
            }
        }

        for (SectionType type : new SectionType[]{SectionType.ACHIEVEMENT, SectionType.QUALIFICATIONS}) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                String[] elements = value.split("\\r?\\n");
                List<String> trimmedElements = new ArrayList<>();
                for (String element : elements) {
                    String trimmed = element.trim();
                    if (!trimmed.isEmpty()) {
                        trimmedElements.add(trimmed);
                    }
                }
                if (!trimmedElements.isEmpty()) {
                    r.setSection(type, new ListSection(trimmedElements));
                } else {
                    r.getSections().remove(type);
                }
            } else {
                r.getSections().remove(type);
            }
        }

        if (isNew) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }

        Resume r;
        boolean isNew = "new".equals(uuid);

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                if (isNew) {
                    r = new Resume("");
                } else {
                    r = storage.get(uuid);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.setAttribute("isNew", isNew);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}