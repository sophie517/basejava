package ru.javawebinar.basejava.model;

public class Period {
    private final String startDate;
    private final String endDate;
    private final String position;
    private final String content;

    public Period(String startDate, String endDate, String position, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        return startDate.equals(period.startDate) &&
               endDate.equals(period.endDate) &&
               position.equals(period.position) &&
               content.equals(period.content);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate + "  " + position + "\n" + content;
    }
}
