package com.genius.utils;

import com.genius.utils.lib.CommonHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonMain {
    public static void main(String[] args) {
        log.info("syskey: " + CommonHandler.getSyskey());
        log.info("Super Admin Raw: " + CommonHandler.getSuperAdmin());
        log.info("Super Admin Json: " + CommonHandler.toJSON(CommonHandler.getSuperAdmin()));
        log.info("Convert 9999 ms: " + CommonHandler.convertMillisecondsToMinuteSecondMillisecond(9999));
    }
}
