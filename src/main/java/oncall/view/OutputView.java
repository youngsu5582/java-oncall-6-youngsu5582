package oncall.view;

public class OutputView {
    private static OutputView INSTANCE = null;
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static OutputView getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutputView();
        }
        return INSTANCE;
    }

    private void printNewLine() {
        System.out.println();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        printMessage(ERROR_PREFIX + errorMessage);
    }

}