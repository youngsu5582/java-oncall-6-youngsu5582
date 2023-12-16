package oncall.util;

import oncall.domain.day.DayInfo;
import oncall.domain.day.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayCalculatorTest {
    @Test
    @DisplayName("DayInfo 를 가지고 , DayCalculator 를 생성한다")
    void createDayCalculator() {
        //Given
        DayInfo dayInfo = new DayInfo(3, DayOfWeek.MONDAY);

        //When
        DayCalculator dayCalculator = new DayCalculator(dayInfo);

        //Then
        Assertions.assertInstanceOf(DayCalculator.class, dayCalculator);
    }

    @Test
    @DisplayName("현재 달의 마지막 날이 알려준다.")
    void getMaxDayInCurrentMonth() {
        //Given
        DayInfo dayInfo = new DayInfo(3, DayOfWeek.MONDAY);
        DayCalculator dayCalculator = new DayCalculator(dayInfo);

        //Then
        Integer maxDay = dayCalculator.getMaxDayInCurrentMonth();

        //When
        Assertions.assertEquals(maxDay, 31);
    }

    @Test
    @DisplayName("시작일을 바탕으로 , 특정일이 무슨 요일인지 알려준다.")
    void calculateDayOfWeekWithDay() {
        //Given
        DayInfo dayInfo = new DayInfo(3, DayOfWeek.MONDAY);
        DayCalculator dayCalculator = new DayCalculator(dayInfo);

        //Then
        DayOfWeek dayOfWeek = dayCalculator.calculateDayOfWeekWithDay(23);

        //When
        Assertions.assertEquals(dayOfWeek, DayOfWeek.TUESDAY);
    }

}
