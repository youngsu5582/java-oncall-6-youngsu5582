package oncall.util;

import oncall.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Integer parseInfoToNumber(String info) {
        try {
            return Integer.parseInt(info);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
        }
    }
    public static List<String> parseInfoWithSeparator(String info, String separator) {
        return Arrays.asList(info.split(separator));
    }
}
