package com.genius.utils.lib;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class MediaHandler {
    // get IMG_20250508015000115.png, VID_20250508015000115.mp4 by Genius iQ @20250515
    public static String generateMediaName(String prefix, String type) {
        String mediaName = "";
        if("txt".equals(type)) {
            mediaName = prefix + "_" + DateTimeHandler.getMyanmarDate() + "." + type;
        } else {
            mediaName = prefix + "_" + DateTimeHandler.getMyanmarTimestamp() + "." + type;
        }

        return mediaName;
    }

    // make directory by Genius iQ @20250515
    public static boolean makeDirectory(String directoryName) {
        boolean status = true;
        File dir = new File(directoryName);
        if(dir.exists()) {
            log.info(directoryName + "/ is already existed!");
        } else {
            status = dir.mkdirs();
        }

        return status;
    }
}
