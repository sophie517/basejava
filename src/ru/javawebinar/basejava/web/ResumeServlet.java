package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        StringBuilder table = new StringBuilder("""
                <html><head>
                <link rel="stylesheet" href="css/style.css">
                <title>Resume Table</title></head><body>
                <h2>Таблица резюме</h2>
                <table style="width:70%" border="1">
                  <tr>
                    <td>UUID</td>
                    <td>FULL NAME</td>
                  </tr>""");

        for (Resume r : storage.getAllSorted()) {
            table.append("""
                <tr>
                <td>""")
                    .append(r.getUuid())
                    .append("""
                </td>
                <td>""")
                    .append(r.getFullName())
                    .append("""
                </td>
                </tr>""");
        }

        table.append("""
                </table>
                </body>
                </html>""");

        response.getWriter().write(table.toString());
    }
}