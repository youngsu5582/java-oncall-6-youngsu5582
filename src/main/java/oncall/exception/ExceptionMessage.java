package oncall.exception;

public enum ExceptionMessage {
    INVALID_FORMAT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}