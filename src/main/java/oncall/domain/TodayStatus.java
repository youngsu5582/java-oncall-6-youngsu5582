package oncall.domain;

import oncall.domain.day.Day;
import oncall.domain.day.DayOfWeek;
import oncall.repository.SpecialDayRepository;


public enum TodayStatus {
    WEEKDAY(""),
    WEEKEND(""),
    SPECIAL("휴일");
    private final String info;

    TodayStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static TodayStatus getStatus(Day day, DayOfWeek dayOfWeek) {
        if (SpecialDayRepository.isSpecialDay(day))
            return SPECIAL;
        if (dayOfWeek.isWeekend())
            return WEEKEND;
        return WEEKDAY;
    }
}
