package com.genius.utils.lib;

import com.google.gson.Gson;
import java.util.LinkedHashMap;

public class CommonHandler {
    // get syskey by Genius iQ @20250508
    public static long getSyskey() {
        return Long.parseLong(DateTimeHandler.getMyanmarTimestamp());
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
}
