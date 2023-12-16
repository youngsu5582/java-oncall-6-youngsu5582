package oncall.domain.day;

import oncall.exception.ExceptionMessage;


public class DayInfo {
    private final Integer currentMonth;
    private final DayOfWeek startDayofWeek;

    public DayOfWeek getStartDayofWeek() {
        return startDayofWeek;
    }

    public Integer getCurrentMonth() {
        return currentMonth;
    }

    public DayInfo(Integer month, DayOfWeek dayOfWeek) {
        validateMonth(month);
        this.currentMonth = month;
        this.startDayofWeek = dayOfWeek;
    }

    private void validateMonth(Integer month) {
        if (month > 12 || month < 1)
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
    }


}
