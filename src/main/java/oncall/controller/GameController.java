package oncall.controller;

import oncall.ApplicationStatus;
import oncall.domain.call.Call;
import oncall.domain.day.DayInfo;
import oncall.domain.worker.WorkerRoll;
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
        ApplicationStatus applicationStatus = progress(ApplicationStatus.EMERGENCY_MONTH);
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
        gameGuide.put(ApplicationStatus.EMERGENCY_MONTH, this::dayInfoSetup);
        gameGuide.put(ApplicationStatus.ONCALL_MEMBER, this::workerSetup);
        gameGuide.put(ApplicationStatus.ONCALL_RESULT, this::callSetup);
        gameGuide.put(ApplicationStatus.APPLICATION_EXIT, this::exit);
    }

    private ApplicationStatus dayInfoSetup() {
        DayInfo dayInfo = inputView.inputDayInfo();
        this.dayCalculator = new DayCalculator(dayInfo);
        return ApplicationStatus.ONCALL_MEMBER;
    }

    private ApplicationStatus workerSetup() {
        WorkerRoll workerRoll = inputView.inputWorkerRoll();
        this.workerRoll = workerRoll;
        return ApplicationStatus.ONCALL_RESULT;
    }

    private ApplicationStatus callSetup() {
        CallGenerator callGenerator = new CallGenerator(this.dayCalculator, this.workerRoll);
        List<Call> callList = callGenerator.process();
        outputView.printCallResult(callList);
        return ApplicationStatus.APPLICATION_EXIT;
    }

    private ApplicationStatus exit() {
        return ApplicationStatus.APPLICATION_EXIT;
    }
}
