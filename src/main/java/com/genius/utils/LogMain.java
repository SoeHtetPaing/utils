package com.genius.utils;

import com.genius.utils.lib.CommonHandler;
import com.genius.utils.lib.LogHandler;
import com.genius.utils.model.Media;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogMain {
    public static void main(String[] args) throws IOException {
        Media media = new Media("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGMAAQAABQABDQottAAAAABJRU5ErkJggg==",
                            "Profile Picture.png", "png");
        LogHandler.start("logs", "MediaLog", "GeniusAPI", "MediaMain", "main");
        LogHandler.write(media, "request");
        LogHandler.write(CommonHandler.getSuperAdmin(), "response");
        LogHandler.end();

        LogHandler.write("Process", "text");

    }
}
