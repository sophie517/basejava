package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype") {
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Почта") {
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }

        @Override
        public String toListHtml(String value) {
            return toLink("mailto:" + value, value); // Без префикса для списка
        }
    },
    LINKEDIN_PROFILE("Профиль LinkedIn") {
        @Override
        protected String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB_PROFILE("Профиль GitHub") {
        @Override
        protected String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW_PROFILE("Профиль Stackoverflow") {
        @Override
        protected String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOME_PAGE("Домашняя страница") {
        @Override
        protected String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toListHtml(String value) {
        return toHtml(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
