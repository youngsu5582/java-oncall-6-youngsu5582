package oncall.util;

import oncall.domain.*;
import oncall.domain.call.Call;
import oncall.domain.day.Day;
import oncall.domain.day.DayOfWeek;
import oncall.domain.worker.WorkerRoll;

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
        Integer maxDay = dayCalculator.getMaxDayInCurrentMonth();
        Integer month = dayCalculator.getCurrentMonth();
        createCallProcesses(maxDay, month);
        return callList;
    }

    private void createCallProcesses(Integer maxDay, Integer month) {
        for (int dayCount = 1; dayCount <= maxDay; dayCount++) {
            Day day = new Day(month, dayCount);
            DayOfWeek dayOfWeek = dayCalculator.calculateDayOfWeekWithDay(dayCount);
            TodayStatus todayStatus = TodayStatus.getStatus(day, dayOfWeek);
            String worker = getWorkerWithStatus(todayStatus);

            callList.add(new Call(day, dayOfWeek, todayStatus, worker));
        }
    }

    private String getWorkerWithStatus(TodayStatus todayStatus) {
        if (todayStatus == TodayStatus.WEEKDAY) {
            return getWeekdayWorker();
        }
        return getWeekendWorker();
    }

    private String getWeekdayWorker() {
        if (!weekdayQueue.isEmpty()) {
            return weekdayQueue.poll();
        }
        String workerName = this.workerRoll.getWeekdayWorker(weekdayCount);
        if (isSameWorker(workerName)) {
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
        if (isSameWorker(workerName)) {
            weekendCount = (weekendCount + 1) % workerSize;
            weekendQueue.add(workerName);
            workerName = this.workerRoll.getWeekendWorker(weekendCount);
        }

        weekendCount = (weekendCount + 1) % workerSize;
        return workerName;
    }

    public boolean isSameWorker(String workerName) {
        if (callList.size() != 0 && callList.get(callList.size() - 1).getWorkerName().equals(workerName)) {
            return true;
        }
        return false;
    }


}
