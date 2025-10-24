package com.genius.utils.lib;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;

@Slf4j
public class LogHandler {
    private static String REQUEST_ID;
    private static String PROJECT_NAME;
    private static String CLASS_NAME;
    private static String METHOD_NAME;
    private static String ABSOLUTE_FILE;
    private static Date START_TIME;

    // set properties by Genius iQ @20250515
    public static void start(String filepath, String filename,
                             String projectName, String className,
                             String methodName) {

        String file = MediaHandler.generateMediaName(filename, "txt");
        if(MediaHandler.makeDirectory(filepath)) {
            REQUEST_ID = "RequestID: " + DateTimeHandler.getMyanmarTimestamp();
            PROJECT_NAME = projectName;
            CLASS_NAME = className;
            METHOD_NAME = "Method: " + methodName;
            ABSOLUTE_FILE = filepath + "/" + file;
            START_TIME = new Date();
            write("Start", "text");
        }

    }

    // write custom logs by Genius iQ @20250515
    public static void write(Object content, String contentType) {
        String fullContent = "";

        if(ABSOLUTE_FILE == null) {
            log.warn("LogHandler not started - call start() first!");
        } else {
            fullContent = DateTimeHandler.getMyanmarHour() + getSpace(1)
                    + DateTimeHandler.getMyanmarMillisecond() + getSpace(3)
                    + putIntoBracket(PROJECT_NAME) + putIntoBracket(CLASS_NAME) + getSpace(5);

            switch (contentType) {
                case "text" -> fullContent += content;
                case "request" -> fullContent += putIntoBracket(REQUEST_ID) + putIntoBracket(METHOD_NAME)
                        + getSpace(5) + getRequestFormat(content);
                case "response" -> fullContent += putIntoBracket(REQUEST_ID) + putIntoBracket(METHOD_NAME)
                        + getSpace(5) + getResponseFormat(content);
            }

           MediaHandler.writeText(ABSOLUTE_FILE, fullContent);
        }

    }

    // clear properties by Genius iQ @20250515 modified @20251024
    public static void end() {
        String duration = CommonHandler.convertMillisecondsToMinuteSecondMillisecond((int)(new Date().getTime() - START_TIME.getTime()));
        String fullContent = putIntoBracket("Execute Time: " + duration) + getSpace(1) + "End";
        write(fullContent, "text");
        REQUEST_ID = "";
        PROJECT_NAME = "";
        CLASS_NAME = "";
        METHOD_NAME =  "";
        ABSOLUTE_FILE = null;
        START_TIME = null;
    }

    // private method by Genius iQ @20250515
    private static String getSpace(int count) {
        return switch (count) {
            case 1 -> " ";
            case 3 -> "   ";
            case 5 -> "     ";
            default -> "\t";
        };
    }

    private static String putIntoBracket(String content) {
        return "[" + content + "]";
    }

    private static String getRequestFormat(Object content) {
        HashMap<String, Object> request = new HashMap<>();
        request.put("Request", content);

        return "RequestParam: " + CommonHandler.toJSON(request);
    }

    private static String getResponseFormat(Object content) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("Response", content);

        return "Output: " + CommonHandler.toJSON(response);
    }

}
