package oncall.view.message;

public enum InputViewMessage {
    EMERGENCY("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    WEEKDAY("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    WEEKEND("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    private final String message;

    InputViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}