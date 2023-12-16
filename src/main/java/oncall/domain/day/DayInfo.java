package oncall.domain.day;

import oncall.exception.ExceptionMessage;


public class DayInfo {
    private static final Integer MAX_MONTH = 12;
    private static final Integer MIN_MONTH = 1;
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
        if (month > MAX_MONTH || month < MIN_MONTH)
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
    }


}
