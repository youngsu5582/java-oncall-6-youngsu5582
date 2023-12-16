package oncall.util;

import oncall.domain.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CallGenerator {
    DayCalculator dayCalculator;
    WorkerRoll workerRoll;
    Integer weekdayCount = 0;
    Integer weekendCount = 0;
    Integer workerSize;
    Queue<String> weekdayQueue = new ArrayDeque<>();
    Queue<String> weekendQueue = new ArrayDeque<>();
    List<Call> callList = new ArrayList<>();

    public CallGenerator(DayCalculator dayCalculator, WorkerRoll workerRoll) {
        this.dayCalculator = dayCalculator;
        this.workerRoll = workerRoll;
        this.workerSize = this.workerRoll.getWorkerSize();
    }

    public List<Call> process() {
        //최대 날짜 계산
        Integer maxDay = dayCalculator.getMaxDayInCurrentMonth();
        //현재 달
        Integer month = dayCalculator.getCurrentMonth();
        for (int dayCount = 1; dayCount <= maxDay; dayCount++) {
            Day day = new Day(month, dayCount);
            DayOfWeek dayOfWeek = dayCalculator.calculateDayOfWeekWithDay(dayCount);
            TodayStatus todayStatus = TodayStatus.getStatus(day, dayOfWeek);
            String worker = null;
            if (dayCount == 7) {
                System.out.println(todayStatus);
            }
            if (todayStatus == TodayStatus.WEEKDAY) {
                worker = getWeekdayWorker();
            } else {
                worker = getWeekendWorker();
            }

            callList.add(new Call(day, dayOfWeek, todayStatus, worker));
        }
        return callList;
    }

    private String getWeekdayWorker() {
        if (!weekdayQueue.isEmpty()) {
            return weekdayQueue.poll();
        }
        String workerName = this.workerRoll.getWeekdayWorker(weekdayCount);
        if (callList.size() != 0 && callList.get(callList.size() - 1).getWorkerName().equals(workerName)) {
            weekdayCount = (weekdayCount + 1) % workerSize;
            weekdayQueue.add(workerName);
            workerName = this.workerRoll.getWeekdayWorker(weekdayCount);
        }
        weekdayCount = (weekdayCount + 1) % workerSize;
        return workerName;
    }

    private String getWeekendWorker() {
        if (!weekendQueue.isEmpty()) {
            return weekendQueue.poll();
        }
        String workerName = this.workerRoll.getWeekendWorker(weekendCount);
        if (callList.size() != 0 && callList.get(callList.size() - 1).getWorkerName().equals(workerName)) {
            weekendCount = (weekendCount + 1) % workerSize;
            weekendQueue.add(workerName);
            workerName = this.workerRoll.getWeekendWorker(weekendCount);
        }

        weekendCount = (weekendCount + 1) % workerSize;
        return workerName;
    }


}
