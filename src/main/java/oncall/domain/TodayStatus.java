package oncall.domain;

import oncall.domain.day.Day;
import oncall.domain.day.DayOfWeek;
import oncall.repository.SpecialDayRepository;


public enum TodayStatus {
    WEEKDAY,
    WEEKEND,
    SPECIAL;

    public static TodayStatus getStatus(Day day, DayOfWeek dayOfWeek) {
        if (SpecialDayRepository.isSpecialDay(day))
            return SPECIAL;
        if (dayOfWeek.isWeekend())
            return WEEKEND;
        return WEEKDAY;
    }
}
