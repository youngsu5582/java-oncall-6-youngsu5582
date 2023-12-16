package oncall.view;

import camp.nextstep.edu.missionutils.Console;

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
}