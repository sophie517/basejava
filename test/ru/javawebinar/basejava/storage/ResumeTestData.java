package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.basejava.model.ContactType.*;
import static ru.javawebinar.basejava.model.SectionType.*;

public class ResumeTestData {
    protected static final Resume RESUME = new Resume("uuid0", "Григорий Кислин");
    static {
        RESUME.setContact(PHONE, "+7(921) 855-0482");
        RESUME.setContact(SKYPE, "skype:grigory.kislin");
        RESUME.setContact(MAIL, "gkislin@yandex.ru");
        RESUME.setContact(LINKEDIN_PROFILE, "https://www.linkedin.com/in/gkislin");
        RESUME.setContact(GITHUB_PROFILE, "https://github.com/gkislin");
        RESUME.setContact(STACKOVERFLOW_PROFILE, "https://stackoverflow.com/users/548473/grigory-kislin");
        RESUME.setContact(HOME_PAGE, "http://gkislin.ru/");

        RESUME.setSection(
                OBJECTIVE,
                new TextSection(
                        "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        RESUME.setSection(
                PERSONAL,
                new TextSection(
                        "Аналитический склад ума, сильная логика, креативность, инициативность. " +
                                "Пурист кода и архитектуры."));

        RESUME.setSection(
                ACHIEVEMENT,
                new ListSection(
                        new ArrayList<>(
                                List.of(
                                        """
                                        Организация команды и успешная реализация Java проектов для сторонних заказчиков:
                                        приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей
                                        спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot +
                                        Vaadin проект для комплексных DIY смет""",
                                        """
                                        С 2013 года: разработка проектов "Разработка Web приложения", "Java Enterprise",
                                        "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
                                        Удаленное взаимодействие (JMS/AKKA)". Организация онлайн стажировок и ведение проектов.
                                        Более 3500 выпускников.""",
                                        """
                                        Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.
                                        Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.""",
                                        """
                                        Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.
                                        Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на
                                        стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных
                                        ERP модулей, интеграция CIFS/SMB java сервера.""",
                                        """
                                        Реализация с нуля Rich Internet Application приложения на стеке технологий JPA, Spring,
                                        Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.""",
                                        """
                                        Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных
                                        сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и
                                        информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для
                                        администрирования и мониторинга системы по JMX (Jython/ Django).""",
                                        """
                                        Реализация протоколов по приему платежей всех основных платежных систем России
                                        (Cyberplat, Eport, Chronopay, Сбербанк), Белоруссии (Erip, Osmp) и Никарагуа."""
                                )
                        )
                )
        );

        RESUME.setSection(
                QUALIFICATIONS,
                new ListSection(
                        new ArrayList<>(
                                List.of(
                                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                                        "DB: PostgreSQL (наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
                                        "MySQL, SQLite, MS SQL, HSQLDB",
                                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                                        """
                                         Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis,
                                         Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink),
                                         Guice, GWT (SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons,
                                         Eclipse SWT, JUnit, Selenium (htmlelements).""",
                                        "Python: Django.",
                                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                                        """
                                         Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB,
                                         StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet,
                                         HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.""",
                                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                                        """
                                         Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher,
                                         Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer""",
                                        """
                                         Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектирования,
                                         архитектурных шаблонов, UML, функционального программирования""",
                                        "Родной русский, английский \"upper intermediate\""
                                )
                        )
                )
        );

        RESUME.setSection(
                EXPERIENCE,
                new CompanySection(
                        new ArrayList<>(
                                List.of(
                                        new Company("Java Online Projects",
                                                "https://javaops.ru/",
                                                List.of(new Period(DateUtil.of(2013, Month.of(10)),
                                                                LocalDate.now(),
                                                                "Автор проекта.",
                                                """
                                                Создание, организация и проведение Java онлайн проектов и стажировок.""")
                                                )
                                        ),
                                        new Company("Wrike",
                                                "https://www.wrike.com/",
                                                List.of(new Period(DateUtil.of(2014, Month.of(10)),
                                                                DateUtil.of(2016, Month.of(1)),
                                                                "Старший разработчик (backend)",
                                                """
                                                Проектирование и разработка онлайн платформы управления проектами Wrike
                                                (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).
                                                Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.""")
                                                )
                                        ),
                                        new Company("RIT Center", "",
                                                List.of(new Period(DateUtil.of(2012, Month.of(4)),
                                                                DateUtil.of(2014, Month.of(10)),
                                                                "Java архитектор",
                                                """
                                                Организация процесса разработки системы ERP для разных окружений:
                                                релизная политика, версионирование, ведение CI (Jenkins), миграция базы
                                                (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO.
                                                Архитектура БД и серверной части системы. Разработка интеграционных сервисов:
                                                CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в
                                                pdf, doc, html). Интеграция Alfresco JLAN для online редактирования из
                                                браузера документов MS Office. Maven + plugin development, Ant, Apache
                                                Commons, Spring security, Spring MVC, Tomcat, WSO2, xcmis, OpenCmis, Bonita,
                                                Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python""")
                                                )
                                        ),
                                        new Company("Luxoft (Deutsche Bank)",
                                                "https://www.luxoft.ru/",
                                                List.of(new Period(DateUtil.of(2010, Month.of(12)),
                                                                DateUtil.of(2012, Month.of(4)),
                                                                "Ведущий программист",
                                                """
                                                Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC,
                                                SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM.
                                                Реализация RIA-приложения для администрирования, мониторинга и анализа
                                                результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC,
                                                GWT, ExtGWT (GXT), Highstock, Commet, HTML5.""")
                                                )
                                        ),
                                        new Company("Yota",
                                                "https://www.yota.ru/",
                                                List.of(new Period(DateUtil.of(2008, Month.of(6)),
                                                                DateUtil.of(2010, Month.of(12)),
                                                                "Ведущий специалист",
                                                """
                                                Дизайн и имплементация Java EE фреймворка для отдела "Платежные Системы"
                                                (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS,
                                                Maven2). Реализация администрирования, статистики и мониторинга фреймворка.
                                                Разработка online JMX клиента (Python/ Jython, Django, ExtJS)""")
                                                )
                                        ),
                                        new Company("Enkata",
                                                "http://enkata.com/",
                                                List.of(new Period(DateUtil.of(2007, Month.of(3)),
                                                                DateUtil.of(2008, Month.of(6)),
                                                                "Разработчик ПО",
                                                """
                                                Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0,
                                                Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).""")
                                                )
                                        ),
                                        new Company("Siemens AG",
                                                "https://www.siemens.com/global/en.html",
                                                List.of(new Period(DateUtil.of(2005, Month.of(1)),
                                                                DateUtil.of(2007, Month.of(2)),
                                                                "Разработчик ПО",
                                                """
                                                 Разработка информационной модели, проектирование интерфейсов,
                                                 реализация и отладка ПО на мобильной IN платформе Siemens @vantage
                                                 (Java, Unix).""")
                                                )
                                        ),
                                        new Company("Alcatel",
                                                "http://www.alcatel.ru/",
                                                List.of(new Period(DateUtil.of(1997, Month.of(9)),
                                                                DateUtil.of(2005, Month.of(1)),
                                                                "Инженер по аппаратному и программному " +
                                                                        "тестированию",
                                                """
                                                 Тестирование, отладка, внедрение ПО цифровой телефонной
                                                 станции Alcatel 1000 S12 (CHILL, ASM).""")
                                                )
                                        )
                                )
                        )
                )
        );

        RESUME.setSection(
                EDUCATION,
                new CompanySection(
                        new ArrayList<>(
                                List.of(
                                        new Company("Coursera",
                                                "https://www.coursera.org/course/progfun",
                                                List.of(new Period(DateUtil.of(2013, Month.of(3)),
                                                                DateUtil.of(2013, Month.of(5)),
                                                        "'Functional Programming Principles in Scala' " +
                                                                "by Martin Odersky", "")
                                                )
                                        ),
                                        new Company("Luxoft",
                                                "https://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                                                List.of(new Period(DateUtil.of(2011, Month.of(3)),
                                                                DateUtil.of(2011, Month.of(4)),
                                                        "Курс 'Объектно-ориентированный анализ ИС. " +
                                                                "Концептуальное моделирование на UML.'", "")
                                                )
                                        ),
                                        new Company("Siemens AG",
                                                "http://www.siemens.ru/",
                                                List.of(new Period(DateUtil.of(2005, Month.of(1)),
                                                                DateUtil.of(2005, Month.of(4)),
                                                                "3 месяца обучения мобильным IN сетям " +
                                                                        "(Берлин)", "")
                                                )
                                        ),
                                        new Company("Alcatel",
                                                "http://www.alcatel.ru/",
                                                List.of(new Period(DateUtil.of(1977, Month.of(9)),
                                                                DateUtil.of(1998, Month.of(3)),
                                                                "6 месяцев обучения цифровым телефонным " +
                                                                        "сетям (Москва)", "")
                                                )
                                        ),
                                        new Company("Санкт-Петербургский национальный исследовательский " +
                                                    "университет информационных технологий, механики и оптики",
                                                "https://itmo.ru/",
                                                List.of(new Period(DateUtil.of(1993, Month.of(9)),
                                                                DateUtil.of(1996, Month.of(7)),
                                                                "Аспирантура (программист С, С++)", ""),
                                                new Period(DateUtil.of(1987, Month.of(9)),
                                                        DateUtil.of(1993, Month.of(7)),
                                                        "Инженер (программист Fortran, C)", "")
                                                )
                                        ),
                                        new Company(
                                                "Заочная физико-техническая школа при МФТИ",
                                                "https://mipt.ru/",
                                                List.of(new Period(DateUtil.of(1984, Month.of(9)),
                                                        DateUtil.of(1987, Month.of(6)),
                                                        "Закончил с отличием", "")
                                                )
                                        )
                                )
                        )
                )
        );
        }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(PHONE, "+0123456789");
        resume.setContact(SKYPE, "skype");
        resume.setContact(MAIL, "mail");
        resume.setContact(LINKEDIN_PROFILE, "linkedin");
        resume.setContact(GITHUB_PROFILE, "github");
        resume.setContact(STACKOVERFLOW_PROFILE, "stackoverflow");
        resume.setContact(HOME_PAGE, "home page");

        resume.setSection(
                OBJECTIVE,
                new TextSection(
                        "objective"));

        resume.setSection(
                PERSONAL,
                new TextSection(
                        "personal"));

        resume.setSection(
                ACHIEVEMENT,
                new ListSection(
                        new ArrayList<>(
                                List.of("achievement0", "achievement1", "achievement3")
                        )
                )
        );

        resume.setSection(
                QUALIFICATIONS,
                new ListSection(
                        new ArrayList<>(
                                List.of("qualification0", "qualification1", "qualification2")
                        )
                )
        );

        resume.setSection(
                EXPERIENCE,
                new CompanySection(
                        new ArrayList<>(
                                List.of(
                                        new Company("company0",
                                                "https://company0.com",
                                                List.of(new Period(DateUtil.of(2015, Month.of(10)),
                                                        DateUtil.of(2017, Month.of(3)),
                                                        "position0",
                                                        "content0")
                                                )
                                        ),
                                        new Company("company1",
                                                "https://company1.com",
                                                List.of(new Period(DateUtil.of(2017, Month.of(4)),
                                                        DateUtil.of(2019, Month.of(8)),
                                                        "position1",
                                                        "content1"),
                                                        new Period(DateUtil.of(2019, Month.of(9)),
                                                                DateUtil.of(2020, Month.of(12)),
                                                                "position1.1",
                                                                "content1.1")
                                                )
                                        ),
                                        new Company("company2",
                                                "https://company2.com",
                                                List.of(new Period(DateUtil.of(2021, Month.of(2)),
                                                        DateUtil.of(2022, Month.of(8)),
                                                                "position2",
                                                                "content2")
                                                )
                                        )
                                )
                        )
                )

        );

        resume.setSection(
                EDUCATION,
                new CompanySection(
                        new ArrayList<>(
                                List.of(
                                        new Company("company0",
                                                "https://company0/edu.com",
                                                List.of(new Period(DateUtil.of(2015, Month.of(10)),
                                                        DateUtil.of(2017, Month.of(3)),
                                                        "position0.edu",
                                                        "content0.edu")
                                                )
                                        ),
                                        new Company("company1",
                                                "https://company1/edu.com",
                                                List.of(new Period(DateUtil.of(2017, Month.of(4)),
                                                                DateUtil.of(2019, Month.of(8)),
                                                                "position1.edu",
                                                                "content1.edu"),
                                                        new Period(DateUtil.of(2019, Month.of(9)),
                                                                DateUtil.of(2020, Month.of(12)),
                                                                "position1.1.edu",
                                                                "content1.1.edu")
                                                )
                                        ),
                                        new Company("company2",
                                                "https://company2/edu.com",
                                                List.of(new Period(DateUtil.of(2021, Month.of(2)),
                                                        DateUtil.of(2022, Month.of(8)),
                                                        "position2.edu",
                                                        "content2.edu")
                                                )
                                        )
                                )
                        )
                )
        );
    return resume;
    }

    public static void main(String[] args) {
        System.out.println(RESUME);
    }
}
