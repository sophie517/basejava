<%@ page contentType="text/html;charset=UTF-8" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">EDIT</a></h2>

    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Section>"/>
        <c:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <h3>${type.title}</h3>
        <c:choose>
            <c:when test="${type == 'PERSONAL' || type == 'OBJECTIVE'}">
                <p>
                    <c:out value="${section.content}"/>
                </p>
            </c:when>

            <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                <ul>
                    <c:forEach var="element" items="${section.elements}">
                        <li><c:out value="${element}"/></li>
                    </c:forEach>
                </ul>
            </c:when>

            <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                <c:forEach var="company" items="${section.companies}">
                    <h4>
                        <c:out value="${company.name}"/>
                        <c:if test="${not empty company.link}">
                            <a href="${company.link}" target="_blank"></a>
                        </c:if>
                    </h4>
                    <c:forEach var="period" items="${company.periods}">
                        <p>
                            <c:out value="${period.position}"/><br/>
                                <c:out value="${period.startDate} – ${period.endDate}"/>
                            <c:if test="${not empty period.description}">
                                <br/>
                                <c:out value="${period.description}"/>
                            </c:if>
                        </p>
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>