package oncall.domain.day;

import oncall.exception.ExceptionMessage;

import java.util.List;

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

    public String getInfo() {
        return info;
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

    public Boolean isWeekend() {
        List<DayOfWeek> weekendList = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        if (weekendList.contains(this)) {
            return true;
        }
        return false;
    }
}

