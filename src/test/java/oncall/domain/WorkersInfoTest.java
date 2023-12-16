package oncall.domain;

import oncall.domain.worker.WorkersInfo;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkersInfoTest {
    @Test
    @DisplayName("이름이 담긴 문자열 배열을 통해 , WorkerInfo 를 생성한다.")
    void createWorkersInfo() {
        List<String> workers = List.of("포비", "크롱", "나나", "영수", "배민");

        WorkersInfo workersInfo = new WorkersInfo(workers);

        Assertions.assertInstanceOf(WorkersInfo.class, workersInfo);
    }

    @Test
    @DisplayName("문자열 배열의 길이가 5보다 작을 시 , 예외를 발생한다.")
    void throwExceptionMinLength() {
        List<String> workers = List.of("포비", "크롱", "나나", "영수");
        assertThatThrownBy(() -> new WorkersInfo(workers))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열 배열의 길이가 35보다 클 시 , 예외를 발생한다.")
    void throwExceptionMaxLength() {
        List<String> workers = IntStream.rangeClosed(1, 36)
                .mapToObj(i -> "이름" + i)
                .toList();

        assertThatThrownBy(() -> new WorkersInfo(workers))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 이름이 있을 시 , 예외를 발생한다.")
    void throwExceptionDuplicate() {
        List<String> workers = List.of("포비", "크롱", "제임스", "제이슨", "포비");
        assertThatThrownBy(() -> new WorkersInfo(workers))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 다섯 글자보다 클 시 , 예외를 발생한다.")
    void throwExceptionMaxNameLength() {
        List<String> workers = List.of("포비", "크롱", "제임스는포비편", "제이슨", "포비");
        assertThatThrownBy(() -> new WorkersInfo(workers))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
