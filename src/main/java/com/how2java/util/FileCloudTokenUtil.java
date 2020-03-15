package com.how2java.util;

import java.util.UUID;

public class FileCloudTokenUtil {
    public static String getToken(String username) {
        return username + "_" + UUID.randomUUID();
    }
}
