package com.genius.utils;

import com.genius.utils.lib.DateTimeHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeMain {
    public static void main(String[] args) {
        log.info("Myanmar Timestamp: " + DateTimeHandler.getMyanmarTimestamp());
        log.info("Myanmar Date: " + DateTimeHandler.getMyanmarDate());
        log.info("Myanmar Hour: " + DateTimeHandler.getMyanmarHour());
        log.info("Myanmar Millisecond: " + DateTimeHandler.getMyanmarMillisecond());
        log.info("Myanmar Date Time: " + DateTimeHandler.getMyanmarDateTime());
        log.info("Zoned Date Time: " + DateTimeHandler.getDateTimeByZone(DateTimeHandler.getMyanmarDateTime(), "Asia/Tokyo"));
    }
}
