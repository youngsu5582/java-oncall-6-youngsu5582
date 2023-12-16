package oncall.domain;

import oncall.domain.day.DayInfo;
import oncall.domain.day.DayOfWeek;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayInfoTest {
    @Test
    @DisplayName("달과 요일을 입력 받아 DayInfo를 만든다.")
    void createDayInfo() {
        //Given
        Integer month = 5;
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;

        //When
        DayInfo dayInfo = new DayInfo(month, dayOfWeek);

        Assertions.assertInstanceOf(DayInfo.class, dayInfo);
    }

    @ParameterizedTest
    @DisplayName("1월과 12월을 넘는 달을 입력하면 예외를 발생한다.")
    @ValueSource(ints = {0, 13})
    void throwExceptionInvalidMonth(Integer invalidMonth) {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;

        //When
        assertThatThrownBy(() -> new DayInfo(invalidMonth, dayOfWeek))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }
//    @ParameterizedTest
//    @DisplayName("해당 요일이 주말인지 알려준다.")
//    @ValueSource(classes = {DayOfWeek.SATURDAY,DayOfWeek.SUNDAY})
//    void isWeekend(){
//
//    }
}

