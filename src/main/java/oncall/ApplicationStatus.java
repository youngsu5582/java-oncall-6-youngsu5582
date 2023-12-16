package oncall;

public enum ApplicationStatus {

    APPLICATION_START,
    EMERGENCY_MONTH,
    ONCALL_MEMBER,
    ONCALL_RESULT,
    APPLICATION_EXIT;

    public boolean playable() {
        return this != APPLICATION_EXIT;
    }
}
