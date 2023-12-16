package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.domain.DayInfo;
import oncall.domain.DayOfWeek;
import oncall.util.Parser;
import oncall.util.Validator;

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
        String dayInfo = readInput();
        Validator.validateInfoWithPattern(dayInfo);
        List<String> dayInfoList = Parser.parseInfoWithSeparator(dayInfo, ",");
        Integer month = Parser.parseInfoToNumber(dayInfoList.get(0));
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeekWithInfo(dayInfoList.get(1));
        return new DayInfo(month, dayOfWeek);
    }

}