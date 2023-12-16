package oncall.service;

import oncall.domain.Day;
import oncall.repository.SpecialDayRepository;

import java.util.List;

public class SpecialDayService {
    public void process() {
        processSpecialDay();
    }

    private void processSpecialDay() {
        List<Integer> specialDayMonthList = List.of(1, 3, 5, 6, 8, 10, 10, 12);
        List<Integer> specialDayDateList = List.of(1, 1, 5, 6, 15, 3, 9, 25);
        for (int index = 0; index < specialDayDateList.size(); index++) {
            addSpecialDay(specialDayMonthList.get(index),specialDayDateList.get(index));
        }

    }

    private void addSpecialDay(Integer month, Integer date) {
        SpecialDayRepository.addSpecialDay(new Day(month, date));
    }

}
