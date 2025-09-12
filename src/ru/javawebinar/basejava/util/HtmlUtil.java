package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.Period;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String formatDates(Period period) {
        return DateUtil.format(period.getStartDate()) + " - " + DateUtil.format(period.getEndDate());
    }
}
