package oncall.domain;

import camp.nextstep.edu.missionutils.Randoms;
import oncall.domain.worker.WorkerRoll;
import oncall.domain.worker.WorkersInfo;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkerRollTest {
    @Test
    @DisplayName("WorkersInfo 를 활용해 WorkerRoll 을 생성한다.")
    void createWorkerRoll() {
        List<String> workerInfoList = List.of("포비", "크롱", "나나", "영수", "배민");
        WorkersInfo weekdayWorkersInfo = new WorkersInfo(workerInfoList);
        WorkersInfo weekendWorkersInfo = new WorkersInfo(Randoms.shuffle(workerInfoList));

        WorkerRoll workerRoll = new WorkerRoll(weekdayWorkersInfo, weekendWorkersInfo);

        Assertions.assertInstanceOf(WorkerRoll.class, workerRoll);
    }

    @Test
    @DisplayName("서로 일치하지 않은 WorkerInfo면 예외를 발생한다.")
    void throwExceptionInvalid() {
        List<String> workerInfoList = List.of("포비", "크롱", "나나", "영수", "배민");
        List<String> anotherWorkerInfoList = List.of("포비", "크롱", "나나", "영슈", "배민");
        WorkersInfo weekdayWorkersInfo = new WorkersInfo(workerInfoList);
        WorkersInfo weekendWorkersInfo = new WorkersInfo(anotherWorkerInfoList);

        assertThatThrownBy(() -> new WorkerRoll(weekdayWorkersInfo, weekendWorkersInfo))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }

}
