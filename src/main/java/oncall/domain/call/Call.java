package oncall.domain.call;

import oncall.domain.TodayStatus;
import oncall.domain.day.Day;
import oncall.domain.day.DayOfWeek;

public class Call {
    private Day day;
    private DayOfWeek dayOfWeek;
    private TodayStatus todayStatus;
    private String workerName;

    public Call(Day day, DayOfWeek dayOfWeek, TodayStatus todayStatus, String workerName) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.todayStatus = todayStatus;
        this.workerName = workerName;
    }

    @Override
    public String toString() {
        Integer month = this.day.month;
        Integer date = this.day.date;
        String dayOfWeekInfo = this.dayOfWeek.getInfo();
        String statusInfo = this.todayStatus.getInfo();
        String workerName = this.workerName;

        return String.format("%d월 %d일 %s%s %s", month, date, dayOfWeekInfo, statusInfo, workerName);
    }


    public String getWorkerName() {
        return workerName;
    }
}
