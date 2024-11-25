package ru.javawebinar.basejava.model;

import com.google.gson.annotations.JsonAdapter;
import ru.javawebinar.basejava.util.JsonLocalDateAdapter;
import ru.javawebinar.basejava.util.XmlLocalDateAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonAdapter(JsonLocalDateAdapter.class)
    private LocalDate endDate;
    private String position;
    private String description;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public Period() {
    }

    public Period(LocalDate startDate, LocalDate endDate, String position, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        return startDate.equals(period.startDate) &&
               endDate.equals(period.endDate) &&
               position.equals(period.position) &&
               description.equals(period.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate + "  " + position + "\n" + description;
    }
}
