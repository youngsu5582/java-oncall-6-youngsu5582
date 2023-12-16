package oncall.domain.worker;

import oncall.exception.ExceptionMessage;

import java.util.List;
import java.util.stream.Collectors;

public class WorkerRoll {
    private List<String> weekdayTurnRoll;
    private List<String> weekendTurnRoll;

    public WorkerRoll(WorkersInfo weekdayInfo, WorkersInfo weekendInfo) {
        validateWorkerRoll(weekdayInfo, weekendInfo);
        this.weekendTurnRoll = weekendInfo.getWorkerNicknames();
        this.weekdayTurnRoll = weekdayInfo.getWorkerNicknames();
    }

    public Integer getWorkerSize() {
        return weekdayTurnRoll.size();
    }

    public String getWeekdayWorker(Integer count) {
        return this.weekdayTurnRoll.get(count);
    }

    public String getWeekendWorker(Integer count) {
        return this.weekendTurnRoll.get(count);
    }

    private void validateWorkerRoll(WorkersInfo weekdayInfo, WorkersInfo weekendInfo) {
        validateEqual(weekdayInfo.getWorkerNicknames(), weekendInfo.getWorkerNicknames());
    }

    private void validateEqual(List<String> weekdayWorkerNicknames, List<String> weekendWorkerNicknames) {
        List<String> sortedList1 = weekdayWorkerNicknames.stream().sorted().collect(Collectors.toList());
        List<String> sortedList2 = weekendWorkerNicknames.stream().sorted().collect(Collectors.toList());
        if (sortedList1.equals(sortedList2)) {
            return;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
    }
}
