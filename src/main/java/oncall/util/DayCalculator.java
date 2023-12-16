package oncall.util;

import oncall.domain.DayInfo;
import oncall.domain.DayOfWeek;

import java.util.List;

public class DayCalculator {
    private static List<Integer> lastDayList = List.of(0, 31, 28, 31, 30, 31, 30, 31, 31, 31, 30, 31, 30, 31);

    private final DayInfo dayInfo;

    public DayCalculator(DayInfo dayInfo) {
        this.dayInfo = dayInfo;
    }

    public DayOfWeek calculateDayOfWeekWithDay(Integer day) {
        DayOfWeek dayOfWeek = this.dayInfo.getStartDayofWeek();
        day = (day - 1) % 7;
        return dayOfWeek.getDayOfWeekWithDiff(day);
    }
}
