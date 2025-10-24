package com.genius.utils.lib;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    // append data to media file by Genius iQ @20251024
    public static boolean writeText(String filePath, String content) {
        boolean status = true;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            log.error("Failed to write file: " + filePath, e);
            status = false;
        }
        
        return status;
    }
}
