package br.cefetrj.aps.crypto.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateUtils {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime parse(String value) {
        return LocalDateTime.parse(value, formatter);
    }

    public static String format(LocalDateTime value) {
        return formatter.format(value);
    }

    public static LocalDateTime getRandomDate(Random random) {
        int year = random.nextInt(22) + 2000;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
