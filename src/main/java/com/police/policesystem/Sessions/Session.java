package com.police.policesystem.Sessions;

import java.util.prefs.Preferences;

public class Session {
    private static final String PREF_NODE_NAME = "com.example.myapp";

    public static void savePreference(String key, String value) {
        Preferences preferences = Preferences.userRoot().node(PREF_NODE_NAME);
        preferences.put(key, value);
    }

    public static String getPreference(String key, String defaultValue) {
        Preferences preferences = Preferences.userRoot().node(PREF_NODE_NAME);
        return preferences.get(key, defaultValue);
    }

    public static void removePreference(String key) {
        Preferences preferences = Preferences.userRoot().node(PREF_NODE_NAME);
        preferences.remove(key);
    }
}
