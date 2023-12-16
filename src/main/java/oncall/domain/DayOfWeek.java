package oncall.domain;

import oncall.exception.ExceptionMessage;

public enum DayOfWeek {
    MONDAY("월", 0),
    TUESDAY("화", 1),
    WEDNESDAY("수", 2),
    THURSDAY("목", 3),
    FRIDAY("금", 4),
    SATURDAY("토", 5),
    SUNDAY("일", 6);
    private String info;
    private Integer number;

    private DayOfWeek(String info, Integer number) {
        this.info = info;
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public static DayOfWeek getDayOfWeekWithInfo(String info) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (info.equals(dayOfWeek.info)) {
                return dayOfWeek;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
    }

    private DayOfWeek findDayOfWeekWithNumber(Integer number) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (dayOfWeek.number.equals(number)) {
                return dayOfWeek;
            }
        }
        return null;
    }

    public DayOfWeek getDayOfWeekWithDiff(Integer diff) {
        int newIndex = (this.number + diff) % 7;
        return findDayOfWeekWithNumber(newIndex);
    }

}

