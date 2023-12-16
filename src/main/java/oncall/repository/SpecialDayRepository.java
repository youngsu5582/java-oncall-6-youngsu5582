package oncall.repository;

import oncall.domain.day.Day;

import java.util.ArrayList;
import java.util.List;

public class SpecialDayRepository {
    private static List<Day> specialDays = new ArrayList<>();

    public static void addSpecialDay(Day specialDay) {
        specialDays.add(specialDay);
    }

    public static boolean isSpecialDay(Day day) {
        for (Day specialDay : specialDays) {
            if (specialDay.date.equals(day.date) && specialDay.month.equals(day.month)) {
                return true;
            }
        }
        return false;
    }
}
