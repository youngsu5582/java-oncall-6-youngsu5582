package oncall.domain;

import oncall.exception.ExceptionMessage;
import oncall.util.Constant;

import java.util.List;

public class WorkersInfo {
    private List<String> workerNicknames;

    public List<String> getWorkerNicknames() {
        return workerNicknames;
    }

    public WorkersInfo(List<String> workerNicknames) {
        validateWorkerNicknames(workerNicknames);
        this.workerNicknames = workerNicknames;
    }

    private void validateWorkerNicknames(List<String> workerNicknames) {
        validateSize(workerNicknames);
        validateDuplicate(workerNicknames);
        validateNicknames(workerNicknames);
    }

    private void validateSize(List<String> workerNicknames) {
        Integer workersSize = workerNicknames.size();
        if (workersSize > Constant.WORKER_MAX_SIZE || workersSize < Constant.WORKER_MIN_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
        }
    }

    private void validateDuplicate(List<String> workerNicknames) {
        if (workerNicknames.stream().distinct().count() != workerNicknames.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
        }
    }

    private void validateNicknames(List<String> workerNicknames) {
        for (String workerNickname : workerNicknames) {
            if (workerNickname.length() > Constant.NICKNAME_MAX_SIZE) {
                throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
            }
        }
    }
}
