package org.ismetg.constant;

import java.util.regex.Pattern;

public class Constants {
    public static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
}
