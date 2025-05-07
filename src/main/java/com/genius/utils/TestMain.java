package com.genius.utils;

import com.genius.utils.lib.CommonHandler;
import com.genius.utils.lib.DateTimeHandler;
import com.genius.utils.lib.MediaHandler;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(DateTimeHandler.getMyanmarTimestamp());
        System.out.println(DateTimeHandler.getCurrentTimestamp());

        System.out.println(CommonHandler.getSyskey());
        System.out.println(CommonHandler.getSuperAdmin());

        System.out.println(MediaHandler.generateMediaName("TRACK", "mp3"));
        System.out.println(MediaHandler.getServerPath());
    }
}
