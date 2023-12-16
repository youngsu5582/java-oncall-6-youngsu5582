package oncall.domain;

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
        String dayOfWeekStr = this.dayOfWeek.getInfo();
        String workerName = this.workerName;

        String statusStr = "";
        if (this.todayStatus == TodayStatus.SPECIAL) {
            statusStr = "(휴일)";
        }

        return String.format("%d월 %d일 %s%s %s", month, date, dayOfWeekStr, statusStr, workerName);
    }


    public String getWorkerName() {
        return workerName;
    }
}
