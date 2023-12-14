package io.hardingadonis.feizh.utils;

import java.time.*;
import java.time.format.*;

public class Converter {

    public static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime convert(String datetime) {
        if (datetime != null) {
            return LocalDateTime.parse(datetime, DATE_TIME_FORMAT);
        }

        return null;
    }
    
    public static String convert(LocalDateTime datetime) {
        if (datetime != null) {
            return datetime.format(DATE_TIME_FORMAT);
        }

        return null;
    }
}
