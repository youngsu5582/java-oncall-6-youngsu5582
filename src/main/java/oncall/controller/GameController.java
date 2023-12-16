package oncall.controller;

import oncall.ApplicationStatus;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> gameGuide;

    public GameController() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
        this.gameGuide = new EnumMap<>(ApplicationStatus.class);
        initializeGameGuide();
    }

    public void service() {
        ApplicationStatus applicationStatus = progress(ApplicationStatus.APPLICATION_START); // 초기 설정
        while (applicationStatus.playable()) {
            applicationStatus = progress(applicationStatus);
        }
    }

    public ApplicationStatus progress(ApplicationStatus applicationStatus) {
        try {
            return gameGuide.get(applicationStatus).get();
        } catch (IllegalArgumentException exception) {
            return applicationStatus;
        }
    }

    private void initializeGameGuide() {
        gameGuide.put(ApplicationStatus.EMERGENCY_MONTH, this::emergency);
    }

    private ApplicationStatus emergency() {
        return ApplicationStatus.ONCALL_MEMBER;
    }
}
