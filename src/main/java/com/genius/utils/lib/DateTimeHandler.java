package com.genius.utils.lib;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    // get YangonTimes by Genius iQ @2025015
    public static ZonedDateTime getMyanmarZonedDateTime() {
        ZoneId myanmarZone = ZoneId.of("Asia/Yangon");
        return ZonedDateTime.now(myanmarZone);
    }
}
