package com.genius.utils.lib;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeHandler {
    // get yyyyMMddHHmmSSS by Genius iQ @20250507
    public static String getMyanmarTimestamp() {
        return getMyanmarZonedDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    // get yyyyMMdd by Genius iQ @20250515
    public static String getMyanmarDate() {
        return getMyanmarZonedDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    // get hh:mm:ss a by Genius iQ @20250515
    public static String getMyanmarHour() {
        return getMyanmarZonedDateTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }

    // get SSSSS by Genius iQ @20250515
    public static String getMyanmarMillisecond() {
        return getMyanmarZonedDateTime().format(DateTimeFormatter.ofPattern("SSSSS")) + " MS";
    }

    // get YYYY-MM-DD hh:mm:ss a by Genius iQ @20251017
    public static String getMyanmarDateTime() {
        return getMyanmarZonedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
    }

    // get target Zoned Time String by Genius iQ @20251017
    public static String getDateTimeByZone(String dateStr, String targetZone) {
        try {
            ZonedDateTime yangonTime = ZonedDateTime.of(
                LocalDateTime.parse(
                    dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                ), ZoneId.of("Asia/Yangon")
            );

            return yangonTime.withZoneSameInstant(ZoneId.of(targetZone)).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH)
                ).toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing or converting date!";
        }
    }

    // get YangonTimes by Genius iQ @2025015
    public static ZonedDateTime getMyanmarZonedDateTime() {
        ZoneId myanmarZone = ZoneId.of("Asia/Yangon");
        return ZonedDateTime.now(myanmarZone);
    }
}
