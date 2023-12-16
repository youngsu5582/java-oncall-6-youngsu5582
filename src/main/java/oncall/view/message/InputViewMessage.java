package oncall.view.message;

public enum InputViewMessage {
    ;
    private final String message;

    InputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}