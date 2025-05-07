package com.genius.utils.lib;

public class CommonHandler {
    // get syskey by Genius iQ @20250508
    public static long getSyskey() {
        return Long.parseLong(DateTimeHandler.getMyanmarTimestamp());
    }

    // get super admin by Genius iQ @20250508
    public static String getSuperAdmin() {
        return "genius.iq";
    }
}
