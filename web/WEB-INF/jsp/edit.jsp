<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
        <h2><c:out value="${isNew ? 'Создание резюме' : 'Редактирование резюме'}"/></h2>

        <form method="post">
            <c:if test="${not empty resume.uuid}">
                <input type="hidden" name="uuid" value="${resume.uuid}">
            </c:if>
        <dl>
            <dt>ФИО</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                ${type.title}
                <dd><input type="text" name="${type.name()}" size=50 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции</h3>

        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <c:if test="${sectionType == 'PERSONAL' || sectionType == 'OBJECTIVE'}">
                <c:set var="section" value="${resume.getSection(sectionType)}"/>
                <h4>${sectionType.title}</h4>
                <textarea name="${sectionType}" rows="4" cols="80"><c:if test="${not empty section}">${section.content}</c:if></textarea>
                <hr>
            </c:if>
        </c:forEach>

            <c:forEach var="sectionType" items="<%=SectionType.values()%>">
                <c:if test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">
                    <c:set var="section" value="${resume.getSection(sectionType)}"/>
                    <h4>${sectionType.title}</h4>
                    <textarea name="${sectionType}" rows="4" cols="80"><c:if test="${not empty section}"><c:forEach var="item" items="${section.elements}" varStatus="loop">${item}<c:if test="${!loop.last}">&#10;</c:if></c:forEach></c:if></textarea>
                    <hr>
                </c:if>
            </c:forEach>

        <button type="submit">Сохранить</button>
        <button type="button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>