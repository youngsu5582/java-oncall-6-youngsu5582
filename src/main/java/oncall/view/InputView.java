package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.DayInfo;
import oncall.domain.DayOfWeek;
import oncall.domain.WorkerRoll;
import oncall.domain.WorkersInfo;
import oncall.util.Parser;
import oncall.util.Validator;
import oncall.view.message.InputViewMessage;

import java.util.List;

public class InputView {
    private static InputView INSTANCE = null;

    public static InputView getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputView();
        }
        return INSTANCE;
    }

    public String readInput() {
        return Console.readLine();
    }

    public String inputCommand() {

        return readInput();
    }

    public DayInfo inputDayInfo() {
        System.out.print(InputViewMessage.EMERGENCY.getMessage());
        String dayInfo = readInput();
        Validator.validateInfoWithPattern(dayInfo);
        List<String> dayInfoList = Parser.parseInfoWithSeparator(dayInfo, ",");
        Integer month = Parser.parseInfoToNumber(dayInfoList.get(0));
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeekWithInfo(dayInfoList.get(1));
        return new DayInfo(month, dayOfWeek);
    }

    public WorkerRoll inputWorkerRoll() {
        WorkersInfo weekdayWorkersInfo = inputWeekdayWorkerInfo();
        WorkersInfo weekendWorkersInfo = inputWeekendWorkerInfo();
        return new WorkerRoll(weekdayWorkersInfo, weekendWorkersInfo);
    }

    public WorkersInfo inputWeekdayWorkerInfo() {
        System.out.print(InputViewMessage.WEEKDAY.getMessage());
        String weekdayInfo = readInput();
        Validator.validateInfoWithPattern(weekdayInfo);
        List<String> weekdayWorkerNicknames = Parser.parseInfoWithSeparator(weekdayInfo, ",");
        return new WorkersInfo(weekdayWorkerNicknames);
    }

    private WorkersInfo inputWeekendWorkerInfo() {
        System.out.print(InputViewMessage.WEEKEND.getMessage());
        String weekendInfo = readInput();
        Validator.validateInfoWithPattern(weekendInfo);
        List<String> weekendWorkerNicknames = Parser.parseInfoWithSeparator(weekendInfo, ",");
        return new WorkersInfo(weekendWorkerNicknames);
    }

}