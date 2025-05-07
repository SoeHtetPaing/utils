package com.genius.utils.lib;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    // get current timestamp by Genius iQ @20250507
    public static String getCurrentTimestamp() {
        return getMyanmarTimestamp().substring(0, 8);
    }

    // get yyyyMMddHHmmSSS by Genius iQ @20250507
    public static String getMyanmarTimestamp() {
        ZoneId myanmarZone = ZoneId.of("Asia/Yangon");
        ZonedDateTime yangonTime = ZonedDateTime.now(myanmarZone);

        return yangonTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

}
