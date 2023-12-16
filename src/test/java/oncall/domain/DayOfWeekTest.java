package oncall.domain;

import oncall.domain.day.DayInfo;
import oncall.domain.day.DayOfWeek;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayOfWeekTest {
    @Test
    @DisplayName("요일 문자열을 요일로 바꿔준다")
    void getDayOfWeekInfo() {
        //Given
        String info = "월";

        //When
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeekWithInfo(info);

        //Then
        Assertions.assertEquals(DayOfWeek.MONDAY, dayOfWeek);
    }

    @ParameterizedTest
    @DisplayName("요일 문자열이 아닌 다른 값을 입력하면 예외를 발생한다.")
    @ValueSource(strings = {"워", "수수"})
    void throwExceptionInvalidDayOfWeek(String invalidDayOfWeek) {

        assertThatThrownBy(() -> DayOfWeek.getDayOfWeekWithInfo(invalidDayOfWeek))
                .hasMessageContaining(ExceptionMessage.INVALID_FORMAT.getMessage())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("해당 요일이 주말인지 알려준다.")
    void isWeekend() {
        //Given
        DayOfWeek monday = DayOfWeek.MONDAY;
        DayOfWeek sunday = DayOfWeek.SUNDAY;

        //When, Then
        Assertions.assertTrue(sunday.isWeekend());
        Assertions.assertFalse(monday.isWeekend());
    }
    @Test
    @DisplayName("해당 요일과 몇일 차이나는 날이 무슨 요일인지 알려준다.")
    void getDayOfWeekWithDiff(){
        DayOfWeek monday = DayOfWeek.MONDAY;

        DayOfWeek dayOfWeek = monday.getDayOfWeekWithDiff(14);

        Assertions.assertTrue(dayOfWeek.equals(monday));
    }
}
