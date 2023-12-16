package oncall.util;

import oncall.exception.ExceptionMessage;

import java.util.regex.Pattern;

public class Validator {
    private static Pattern pattern = Pattern.compile("^([a-zA-Z가-힣\\d]+,)*[a-zA-Z가-힣\\d]+$");

    public static void validateInfoWithPattern(String info) {
        if (!pattern.matcher(info).matches())
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
    }
}
