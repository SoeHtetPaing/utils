package com.genius.utils;

import com.genius.utils.lib.CommonHandler;
import com.genius.utils.lib.MediaHandler;
import com.genius.utils.model.Media;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MediaMain {
    public static void main(String[] args) throws IOException {
        log.info(MediaHandler.generateMediaName("IMG", "png"));
        log.info("Directory Making Status: " + MediaHandler.makeDirectory("logs"));
    }
}
