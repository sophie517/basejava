<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/theme/${sessionScope.theme}.css">
    <link rel="stylesheet" href="css/view-resume-styles.css">
    <link rel="stylesheet" href="css/styles.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<section>
    <h2>
        ${resume.fullName}
        <a href="resume?uuid=${resume.uuid}&action=edit" class="icon-btn" title="Редактировать резюме">🖋</a>
    </h2>

    <div class="resume-contacts">
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <c:if test="${not empty resume.getContact(type)}">
                <div class="contact-item">
                    <c:choose>
                        <c:when test="${type == 'PHONE'}">
                            ${type.title}: ${resume.getContact(type)}
                        </c:when>
                        <c:when test="${type == 'MAIL' || type == 'SKYPE'}">
                            ${type.title}:
                            <a href="${resume.getContact(type)}" target="_blank">${resume.getContact(type)}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${resume.getContact(type)}" target="_blank">${type.title}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <c:forEach var="sectionType" items="<%=SectionType.values()%>">
        <c:set var="section" value="${resume.getSection(sectionType)}"/>
        <c:if test="${section != null}">
            <div class="form-section">
                <h3>${sectionType.title}</h3>

                <c:choose>
                    <c:when test="${sectionType == 'OBJECTIVE' || sectionType == 'PERSONAL'}">
                        <p>${section}</p>
                    </c:when>

                    <c:when test="${sectionType == 'ACHIEVEMENT' || sectionType == 'QUALIFICATIONS'}">
                        <ul>
                            <c:forEach var="item" items="${section.elements}">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </c:when>

                    <c:when test="${sectionType == 'EXPERIENCE' || sectionType == 'EDUCATION'}">
                        <c:forEach var="company" items="${section.companies}">
                            <div class="company-card">
                                <h4>
                                    <c:choose>
                                        <c:when test="${not empty company.link.url}">
                                            <a href="${company.link.url}" target="_blank">${company.name}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span>${company.name}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </h4>

                                <c:forEach var="period" items="${company.periods}">
                                    <div class="resume-period">
                                        <div class="resume-period-dates">
                                            <span>${company.formatPeriod(period)}</span>
                                        </div>
                                        <div class="resume-period-content">
                                            <strong>${period.position}</strong>
                                            <div class="resume-description">${period.description}</div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </c:if>
    </c:forEach>
</section>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
