package com.networking.meetingclient.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekUtil {
    public static final Map<Integer, LocalDate> dateMap = new HashMap<>();

    static {
        LocalDate startDate = LocalDate.parse("04/09/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for (int i = 1; i <= 52; i++) {
            dateMap.put(i, startDate);
            startDate = startDate.plusDays(7);
        }
    }

    public static Integer getWeek(LocalDate localDate) {
        for (Map.Entry<Integer, LocalDate> entry : dateMap.entrySet()) {
            if (entry.getValue().isBefore(localDate) && entry.getValue().plusDays(7).isAfter(localDate) || entry.getValue().isEqual(localDate)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static List<String> getWeeks() {
        return dateMap.keySet().stream().map(String::valueOf).toList();
    }

    public static LocalDate getStartDateOfWeek(String selectedWeek) {
        if (selectedWeek == null) {
            return null;
        }
        return dateMap.get(Integer.parseInt(selectedWeek));
    }

    public static LocalDate getEndDateOfWeek(String selectedWeek) {
        if (selectedWeek == null) {
            return null;
        }
        return dateMap.get(Integer.parseInt(selectedWeek)).plusDays(6);
    }
}
