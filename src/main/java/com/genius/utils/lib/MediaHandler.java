package com.genius.utils.lib;

import java.io.File;

public class MediaHandler {
    // get IMG_20250508015000115.png, VID_20250508015000115.mp4 by Genius iQ @20250508
    public static String generateMediaName(String prefix, String type) {
        return prefix + "_" + DateTimeHandler.getMyanmarTimestamp() + "." + type;
    }
}
