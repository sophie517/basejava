<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.CompanySection" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/theme/${sessionScope.theme}.css">
    <link rel="stylesheet" href="css/edit-resume-styles.css">
    <link rel="stylesheet" href="css/styles.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>${isNew ? "Создание резюме" : "Редактирование резюме"}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<section>
    <h2><c:out value="${isNew ? 'Создание резюме' : 'Редактирование резюме'}"/></h2>

    <form method="post" class="resume-form">
        <c:if test="${not empty resume.uuid}">
            <input type="hidden" name="uuid" value="${resume.uuid}">
        </c:if>

        <div class="form-group">
            <input type="text" id="fullName" name="fullName"
                   value="${resume.fullName}" placeholder="ФИО" required>
        </div>

        <h3>Контакты</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <div class="form-group">
                <input type="text" id="${type.name()}" name="${type.name()}"
                       value="${resume.getContact(type)}" placeholder="${type.title}">
            </div>
        </c:forEach>

        <h3>Секции</h3>
        <c:forEach var="sectionType" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(sectionType)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
            <div class="form-section">
                <h4>${sectionType.title}</h4>

                <c:choose>
                    <c:when test="${sectionType == 'OBJECTIVE'}">
                        <input type="text" name="${sectionType}" value='<%=section%>'>
                    </c:when>

                    <c:when test="${sectionType == 'PERSONAL'}">
                        <textarea name="${sectionType}" rows="4"><%=section%></textarea>
                    </c:when>

                    <c:when test="${sectionType == 'ACHIEVEMENT'}">
                        <textarea name="${sectionType}" rows="5"><%=String.join("\n", ((ListSection) section).getElements())%></textarea>
                    </c:when>

                    <c:when test="${sectionType == 'QUALIFICATIONS'}">
                        <textarea name="${sectionType}" rows="5"><%=String.join("\n", ((ListSection) section).getElements())%></textarea>
                    </c:when>

                    <c:when test="${sectionType == 'EXPERIENCE' || sectionType == 'EDUCATION'}">
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>" varStatus="counter">
                            <div class="form-subsection">
                                <div class="form-group">
                                    <input type="text" name="${sectionType}" value="${company.link.name}"
                                           placeholder="Название учреждения">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="${sectionType}url" value="${company.link.url}"
                                           placeholder="Сайт учреждения">
                                </div>

                                <c:forEach var="period" items="${company.periods}">
                                    <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Period"/>

                                    <div class="form-grid">
                                        <div class="form-group">
                                            <input type="text" name="${sectionType}${counter.index}startDate"
                                                   value="<%=DateUtil.format(period.getStartDate())%>"
                                                   placeholder="Начало (MM/YYYY)">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="${sectionType}${counter.index}endDate"
                                                   value="<%=DateUtil.format(period.getEndDate())%>"
                                                   placeholder="Окончание (MM/YYYY)">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="text" name="${sectionType}${counter.index}position"
                                               value="${period.position}" placeholder="Заголовок">
                                    </div>
                                    <div class="form-group">
                                        <textarea name="${sectionType}${counter.index}description" rows="4"
                                                  placeholder="Описание">${period.description}</textarea>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </c:forEach>

        <div class="form-actions">
            <button type="submit">Сохранить</button>
            <button type="button" onclick="window.history.back()">Отменить</button>
        </div>
    </form>
</section>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
