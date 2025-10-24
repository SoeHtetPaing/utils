package com.genius.utils.lib;

import com.google.gson.Gson;
import java.util.LinkedHashMap;

public class CommonHandler {
    // get syskey by Genius iQ @20250508
    public static long getSyskey() {
        return Long.parseLong(DateTimeHandler.getMyanmarTimestamp().substring(2, 17));
    }

    // get super admin by Genius iQ @20250515
    public static LinkedHashMap<String, Object> getSuperAdmin() {
        LinkedHashMap<String, Object> superAdmin = new LinkedHashMap<String, Object>();
        superAdmin.put("syskey", 1);
        superAdmin.put("userid", "genius.iq");
        superAdmin.put("username", "Soe Htet Paing");

        return superAdmin;
    }

    // Object to JSON by Genius iQ @20250515
    public static String toJSON(Object object) {
        return new Gson().toJson(object);
    }

    // convert millsecond to 0m 0s 10 ms format by Genius iQ @20251024
    public static String convertMillisecondsToMinuteSecondMillisecond(int ms) {
        if (ms < 1000) {
            return ms + " ms";
        }

        int totalsec = ms / 1000;
        int min = totalsec / 60;
        double sec = (ms % 60000) / 1000.0;

        if (min == 0) {
            return String.format("%.2f sec", sec);
        }

        return min + " min " + String.format("%.2f", sec) + " sec";
    }
}
