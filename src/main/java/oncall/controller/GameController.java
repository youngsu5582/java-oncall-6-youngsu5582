package oncall.controller;

import oncall.ApplicationStatus;
import oncall.domain.Call;
import oncall.domain.DayInfo;
import oncall.domain.WorkerRoll;
import oncall.util.CallGenerator;
import oncall.util.DayCalculator;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> gameGuide;
    private DayCalculator dayCalculator;
    private WorkerRoll workerRoll;

    public GameController() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
        this.gameGuide = new EnumMap<>(ApplicationStatus.class);
        initializeGameGuide();
    }

    public void service() {
        ApplicationStatus applicationStatus = progress(ApplicationStatus.EMERGENCY_MONTH); // 초기 설정
        while (applicationStatus.playable()) {
            applicationStatus = progress(applicationStatus);
        }
    }

    public ApplicationStatus progress(ApplicationStatus applicationStatus) {
        try {
            return gameGuide.get(applicationStatus).get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return applicationStatus;
        }
    }

    private void initializeGameGuide() {
        gameGuide.put(ApplicationStatus.EMERGENCY_MONTH, this::emergency);
        gameGuide.put(ApplicationStatus.ONCALL_MEMBER, this::oncallMember);
        gameGuide.put(ApplicationStatus.ONCALL_RESULT, this::oncallResult);
        gameGuide.put(ApplicationStatus.APPLICATION_EXIT, this::exit);
    }

    private ApplicationStatus emergency() {
        DayInfo dayInfo = inputView.inputDayInfo();
        this.dayCalculator = new DayCalculator(dayInfo);
        return ApplicationStatus.ONCALL_MEMBER;
    }

    private ApplicationStatus oncallMember() {
        WorkerRoll workerRoll = inputView.inputWorkerRoll();
        this.workerRoll = workerRoll;
        return ApplicationStatus.ONCALL_RESULT;
    }

    private ApplicationStatus oncallResult() {
        CallGenerator callGenerator = new CallGenerator(this.dayCalculator, this.workerRoll);
        List<Call> callList = callGenerator.process();
        for (Call call : callList) {
            System.out.println(call);
        }
        return ApplicationStatus.APPLICATION_EXIT;
    }

    private ApplicationStatus exit() {
        return ApplicationStatus.APPLICATION_EXIT;
    }
}
