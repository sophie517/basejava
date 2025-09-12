<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/theme/${sessionScope.theme}.css">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <link rel="stylesheet" href="css/styles.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="top-controls">
    <a href="resume?uuid=new&action=edit" class="button-add">
        <span class="icon">➕</span> Добавить резюме
    </a>

    <div class="theme-switcher">
        <form method="get" style="display:inline;">
            <label for="theme-select">Тема:</label>
            <select id="theme-select" name="theme" onchange="this.form.submit()">
                <option value="light" ${sessionScope.theme == 'light' ? 'selected' : ''}>🌞 Светлая</option>
                <option value="dark" ${sessionScope.theme == 'dark' ? 'selected' : ''}>🌙 Тёмная</option>
            </select>
        </form>
    </div>
</div>

<section>
    <table class="resume-table">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Контакты</th>
            <th class="icon-column">Редактировать</th>
            <th class="icon-column">Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
            <tr>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a>
                </td>
                <td>
                    <%=ContactType.MAIL.toListHtml(resume.getContact(ContactType.MAIL))%>
                </td>
                <td class="icon-column">
                    <a href="resume?uuid=${resume.uuid}&action=edit" class="icon-btn" title="Редактировать">
                        🖋️
                    </a>
                </td>
                <td class="icon-column">
                    <a href="resume?uuid=${resume.uuid}&action=delete" class="icon-btn" title="Удалить">
                        🗑️
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
