package oncall.util;

import oncall.domain.day.DayInfo;
import oncall.domain.day.DayOfWeek;

import java.util.List;

public class DayCalculator {
    private static List<Integer> lastDayList = List.of(0, 31, 28, 31, 30, 31, 30, 31, 31, 31, 30, 31, 30, 31);

    public Integer getMaxDayInCurrentMonth() {
        return lastDayList.get(this.dayInfo.getCurrentMonth());
    }

    public Integer getCurrentMonth() {
        return this.dayInfo.getCurrentMonth();
    }

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
