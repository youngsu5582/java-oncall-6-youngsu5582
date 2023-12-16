package oncall.util;

import oncall.domain.DayOfWeek;

import java.util.List;

public class DayCalculator {
    private static List<Integer> lastDayList = List.of(0, 31, 28, 31, 30, 31, 30, 31, 31, 31, 30, 31, 30, 31);

    private final Integer currentMonth;
    private final DayOfWeek startDayofWeek;

    public DayCalculator(Integer month, DayOfWeek dayOfWeek) {
        this.currentMonth = month;
        this.startDayofWeek = dayOfWeek;
    }

    public DayOfWeek calculateDayOfWeekWithDay(Integer day) {
        day = (day - 1) % 7;
        return startDayofWeek.getDayOfWeekWithDiff(day);
    }
}
